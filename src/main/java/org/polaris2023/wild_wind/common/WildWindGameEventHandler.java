package org.polaris2023.wild_wind.common;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.init.*;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.network.packets.EggShootPacket;
import org.polaris2023.wild_wind.util.EnchantmentHelper;
import org.polaris2023.wild_wind.util.RegistryUtil;
import org.polaris2023.wild_wind.util.TeleportUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.dyed.handler.RightClickHandler.rightClick;

@EventBusSubscriber(modid = WildWindMod.MOD_ID)
public class WildWindGameEventHandler {

    @SubscribeEvent
    public static void tooltipAdd(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> toolTip = event.getToolTip();
        if (stack.has(DataComponents.FOOD)) {
            FoodProperties foodProperties = stack.get(DataComponents.FOOD);
            assert foodProperties != null;
            toolTip.addLast(Component.empty().append(ModTranslateKey.NUTRITION.translatable()).append(String.valueOf(foodProperties.nutrition())));
            toolTip.addLast(Component.empty().append(ModTranslateKey.SATURATION.translatable()).append(String.valueOf(foodProperties.saturation())));
            List<FoodProperties.PossibleEffect> effects = foodProperties.effects();
            if (!effects.isEmpty()) {
                toolTip.addLast(ModTranslateKey.EFFECT.translatable());
                for (FoodProperties.PossibleEffect effect : effects) {
                    MobEffectInstance effected = effect.effect();
                    toolTip.addLast(Component
                            .empty()
                            .append(effect.probability() * 100 + "% ")
                            .append(Component.translatable(effected.getDescriptionId()))
                            .append((effected.getAmplifier() + 1) + " ")
                            .append(String.valueOf(effected.getDuration()))
                            .append("tick")
                    );
                }
            }

        }
        componentAdd(stack, toolTip, ModTranslateKey.MEAT_VALUE, ModComponents.MEAT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.VEGETABLE_VALUE, ModComponents.VEGETABLE_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FRUIT_VALUE, ModComponents.FRUIT_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.PROTEIN_VALUE, ModComponents.PROTEIN_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.FISH_VALUE, ModComponents.FISH_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.MONSTER_VALUE, ModComponents.MONSTER_VALUE, 0F);
        componentAdd(stack, toolTip, ModTranslateKey.SWEET_VALUE, ModComponents.SWEET_VALUE, 0F);

    }

    @SuppressWarnings("SameParameterValue")
    private static <T> void componentAdd(ItemStack stack, List<Component> toolTip, ModTranslateKey key, Supplier<DataComponentType<T>> data, T defaultT) {
        if (stack.has(data)) {
            toolTip.addLast(Component
                    .empty()
                    .append(key.translatable())
                    .append(String.valueOf(stack.getOrDefault(data, defaultT))));
        }
    }

    @SubscribeEvent
    public static void hurtEvent(LivingDamageEvent.Post event) {
        if (event.getEntity() instanceof Firefly firefly) {
            firefly.clearTicker();
        }
    }

    @SubscribeEvent
    public static void clickEventEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        ItemStack itemstack = event.getItemStack();
        Player player = event.getEntity();
        if (itemstack.is(Items.EGG)) {
            PacketDistributor.sendToServer(new EggShootPacket(itemstack));
            player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
            itemstack.consume(1, player);
        }
//        eggShoot(event.getItemStack(), event.getEntity(), event.getLevel());
    }

    @SubscribeEvent
    public static void clickEventBlock(PlayerInteractEvent.LeftClickBlock event) {
        eggShoot(event.getItemStack(), event.getEntity(), event.getLevel());
    }
    @SubscribeEvent
    public static void rightClickEventBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();
        Direction face = event.getFace();
        if (stack.is(Items.MILK_BUCKET) && !player.isCrouching() && face != null) {

            BlockPos relative = event.getPos().relative(face);
            if (level.getBlockState(relative).canBeReplaced()) {
                event.getLevel().setBlockAndUpdate(relative, ModFluids.MILK_BLOCK.get().defaultBlockState());
                player.setItemInHand(event.getHand(), Items.BUCKET.getDefaultInstance());
                event.setCanceled(true);
            }

        }
    }

    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        eggShoot(player.getMainHandItem(), player, player.level());
    }

    public static void eggShoot(ItemStack itemstack, Player player, Level level) {
        if (itemstack.is(Items.EGG)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                ThrownEgg thrownegg = new ThrownEgg(level, player);
                thrownegg.setItem(itemstack);
                thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(thrownegg);
            }

            player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
            itemstack.consume(1, player);
        }
    }

    private static final ResourceLocation VANILLA_FISHERMAN = ResourceLocation.withDefaultNamespace("fisherman");
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        ResourceLocation currentVillagerProfession = RegistryUtil.getDefaultRegisterName(event.getType());
        //TODO: add villager trades
        if(VANILLA_FISHERMAN.equals(currentVillagerProfession)) {
        }
    }

    @SubscribeEvent
    public static void playerUseItem(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;
        //common code


        // end common code
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        // server code
        ItemStack resultStack = event.getResultStack();
        if (resultStack.has(DataComponents.DAMAGE) && EnchantmentHelper.hasEnchantment(serverLevel, resultStack, ModEnchantments.RUSTY.get())) {
            ItemEnchantments enchantments = resultStack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            if (!enchantments.isEmpty()) {
                for (var entry : enchantments.entrySet()) {
                    ResourceKey<Enchantment> key = entry.getKey().getKey();
                    if (key != null && key.isFor(ModEnchantments.RUSTY.get().registryKey())) {
                        int level = entry.getIntValue();// 0 1 2
                        resultStack.setDamageValue(resultStack.getDamageValue() + level + 1);
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public static void playerBreakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (event.getLevel() instanceof  ServerLevel serverLevel) {

            ItemStack mainHandItem = player.getMainHandItem();

            if (EnchantmentHelper.hasEnchantment(serverLevel, mainHandItem, ModEnchantments.AUTO_SMELTING.get())) {
                BlockPos pos = event.getPos();

                if (
                        isTool(mainHandItem, ItemTags.PICKAXES, BlockTags.MINEABLE_WITH_PICKAXE, serverLevel, pos)
                        || isTool(mainHandItem, ItemTags.AXES, BlockTags.MINEABLE_WITH_AXE, serverLevel, pos)
                        || isTool(mainHandItem, ItemTags.SHOVELS, BlockTags.MINEABLE_WITH_SHOVEL, serverLevel, pos)
                        || isTool(mainHandItem, ItemTags.HOES, BlockTags.MINEABLE_WITH_HOE, serverLevel, pos)
                        || nullTool(mainHandItem, serverLevel, pos, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_SHOVEL, BlockTags.MINEABLE_WITH_HOE)
                ) {

                    //获取掉落物
                    autoSmelting(serverLevel, pos, mainHandItem, player);
                    serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    event.setCanceled(true);

                }
            }
        }
    }

    private static boolean isTool(ItemStack stack, TagKey<Item> itemTag, TagKey<Block> blockTag, ServerLevel serverLevel, BlockPos pos) {
        return stack.is(itemTag) && serverLevel.getBlockState(pos).is(blockTag);
    }

    @SafeVarargs
    private static boolean nullTool(ItemStack stack, ServerLevel level, BlockPos pos, TagKey<Block>... blockTags) {
        BlockState state = level.getBlockState(pos);

        for (TagKey<Block> blockTag : blockTags) {
            if (state.is(blockTag)) {
                return false;
            }
        }
        return stack.is(ItemTags.AXES);
    }

    private static void autoSmelting(ServerLevel serverLevel, BlockPos pos, ItemStack mainHandItem, Player player) {
        if (player.isCreative())
            return;
        List<ItemStack> drops = Block.getDrops(serverLevel.getBlockState(pos), serverLevel, pos, serverLevel.getBlockEntity(pos), player, mainHandItem);

        for (ItemStack drop : drops) {
            serverLevel
                    .getRecipeManager()
                    .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(drop), serverLevel).ifPresentOrElse(h -> {
                        ItemStack resultItem = h.value().getResultItem(serverLevel.registryAccess());


                        int neoCount = 0;
                        int count = drop.getCount();
                        for (;count > 0; count--) {

                            if (mainHandItem.getDamageValue() == mainHandItem.getMaxDamage()) {
                                break;
                            }
                            int enchantmentLevel = mainHandItem.getEnchantmentLevel(serverLevel.registryAccess().holderOrThrow(Enchantments.UNBREAKING));
                            if (enchantmentLevel != 0) {
                                double ran = ((double) 100 / (enchantmentLevel + 1)) / 100;
                                if (serverLevel.random.nextDouble() <= ran) {
                                    mainHandItem.setDamageValue(mainHandItem.getDamageValue() + 1);
                                }
                            } else {
                                mainHandItem.setDamageValue(mainHandItem.getDamageValue() + 1);
                            }
                            neoCount++;
                        }
                        resultItem.setCount(neoCount);
                        Block.popResource(serverLevel, pos, resultItem);
                        drop.setCount(count);
                        Block.popResource(serverLevel, pos, drop);
                    }, () -> Block.popResource(serverLevel, pos, drop));
        }
    }

    @SubscribeEvent
    private static void onEntityTick(EntityTickEvent.Pre event) {
        Entity entity = event.getEntity();
        if (entity instanceof Goat || entity instanceof Cow) {
            AttachmentType<Integer> type = ModAttachmentTypes.MILKING_INTERVALS.get();
            entity.setData(type, Math.max(0, entity.getData(type) - 1));
        } else if (entity instanceof Squid squid) {
            AttachmentType<Integer> type1 = ModAttachmentTypes.SQUID_CONVERSION_TIME.get();
            AttachmentType<Boolean> type2 = ModAttachmentTypes.SHOULD_SQUID_CONVERT.get();
            squid.setData(type1, Math.max(0, squid.getData(type1) - 1));
            Level level = squid.level();
            if (squid.getData(type1) <= 0 && squid.getData(type2)) {
                GlowSquid glowSquid = squid.convertTo(EntityType.GLOW_SQUID, Boolean.FALSE);
                if (glowSquid != null) {
                    EventHooks.onLivingConvert(squid, glowSquid);
                }

                if (!squid.isSilent()) {
                    level.playSound(null, squid.blockPosition(), SoundEvents.GLOW_INK_SAC_USE, SoundSource.NEUTRAL);
                }
            }

            if (squid.getData(type1) > 0 && !squid.getData(type2) && level.isClientSide) {
                level.addParticle(ParticleTypes.GLOW, squid.getRandomX(0.6), squid.getRandomY(), squid.getRandomZ(0.6), 0.0F, 0.0F, 0.0F);
            }

        } else if (entity instanceof ItemEntity item) {
            if (item.getItem().is(ModItems.LIVING_TUBER)) {
                RandomSource random = item.getRandom();
                Level level = item.level();
                int j = random.nextInt(20, 200);
                if (level.getGameTime() % j == 0) {
                    int i = random.nextInt(1, 13);
                    ModSounds sounds = ModSounds.AMBIENT_S.getOrDefault(i, ModSounds.GLARE_AMBIENT_1);
                    level.playLocalSound(item.getX(), item.getY(), item.getZ(), sounds.get(), SoundSource.HOSTILE, 1F, 1F, true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onFinishUsingItem(LivingEntityUseItemEvent.Finish event) {
        Item item = event.getItem().getItem();
        LivingEntity livingEntity = event.getEntity();
        if(item.equals(Items.POPPED_CHORUS_FRUIT)) {
            if(livingEntity.level() instanceof ServerLevel serverLevel) {
                if(TeleportUtil.tryTeleportToSurface(livingEntity, serverLevel, livingEntity.getOnPos()) || TeleportUtil.randomTeleportAround(livingEntity, serverLevel)) {
                    serverLevel.gameEvent(GameEvent.TELEPORT, livingEntity.position(), GameEvent.Context.of(livingEntity));
                    SoundSource soundsource;
                    SoundEvent soundevent;
                    if (livingEntity instanceof Fox) {
                        soundevent = SoundEvents.FOX_TELEPORT;
                        soundsource = SoundSource.NEUTRAL;
                    } else {
                        soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundsource = SoundSource.PLAYERS;
                    }

                    serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, soundsource);
                    livingEntity.resetFallDistance();
                }

                if (livingEntity instanceof Player player) {
                    player.resetCurrentImpulseContext();
                    player.getCooldowns().addCooldown(item, 20);
                }
            }
        } else if(item.equals(Items.GLISTERING_MELON_SLICE)) {
            if(!livingEntity.level().isClientSide) {
                livingEntity.heal(1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack itemStack = player.getMainHandItem();
        BlockPos pos = event.getPos();
        BlockState blockState = level.getBlockState(pos);
        if (event.getHand() == InteractionHand.MAIN_HAND) {
            if (itemStack.getItem() instanceof DyeItem) {
                if (!level.isClientSide) {
                    rightClick(player, level, itemStack, pos, blockState,event);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        AttachmentType<Integer> type = ModAttachmentTypes.MILKING_INTERVALS.get();
        ItemStack itemInHand = player.getItemInHand(event.getHand());
        if (target instanceof Goat || target instanceof Cow) {
            if (itemInHand.is(Items.BUCKET) && !((AgeableMob) target).isBaby()) {
                if (target.getData(type) > 0) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        ModTranslateKey key = target instanceof Goat ?
                                ModTranslateKey.GOAT_INTOLERANCE :
                                ModTranslateKey.COW_INTOLERANCE;
                        serverPlayer.sendSystemMessage(key.translatable());
                        serverPlayer.swing(event.getHand(), Boolean.TRUE);
                    }

                    event.setCanceled(true);
                } else {
                    target.setData(type, 6000);
                }
            }
        } else if (target instanceof MushroomCow mushroomCow) {
            if (itemInHand.is(Items.BOWL) && !mushroomCow.isBaby()) {
                if (target.getData(type) > 0) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        serverPlayer.sendSystemMessage(ModTranslateKey.MOOSHROOM_COW_INTOLERANCE.translatable());
                        serverPlayer.swing(event.getHand(), Boolean.TRUE);
                    }

                    event.setCanceled(true);
                } else {
                    target.setData(type, 6000);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event){
        Player player = event.getEntity();
        Level level = player.level();
        Entity shulker = event.getTarget();
        ItemStack dyeItem = player.getMainHandItem();
        if (event.getHand() == InteractionHand.MAIN_HAND){
            if(!level.isClientSide) {
                if (dyeItem.getItem() instanceof DyeItem) {
                    if (!((Shulker) shulker).getVariant().equals(Optional.ofNullable(DyeColor.getColor(dyeItem)))){
                        if(shulker instanceof Shulker){
                            ((Shulker) shulker).setVariant(Optional.ofNullable(DyeColor.getColor(dyeItem)));
                            if(!player.isCreative()){
                                dyeItem.shrink(1);
                            }
                            player.awardStat(Stats.ITEM_USED.get(dyeItem.getItem()),1);
                            player.playSound(SoundEvents.DYE_USE);
                        }
                    }
                }
            }
        }
    }

}
