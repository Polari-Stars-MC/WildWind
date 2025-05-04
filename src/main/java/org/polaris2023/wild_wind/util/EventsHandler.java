package org.polaris2023.wild_wind.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.polaris2023.wild_wind.client.ModTranslateKey;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/05/04 23:54:58}
 */
public class EventsHandler {

    private static <T extends AbstractCookingRecipe> @NotNull Consumer<RecipeHolder<T>> furnaceImpl(ServerLevel serverLevel, BlockPos pos, ItemStack mainHandItem, ItemStack drop) {
        return h -> {
            T value = h.value();
            ItemStack resultItem = value.getResultItem(serverLevel.registryAccess());

            int neoCount = 0;
            int count = drop.getCount();
            for (; count > 0; count--) {

                if (mainHandItem.getDamageValue() == mainHandItem.getMaxDamage()) {
                    break;
                }
                int enchantmentLevel = mainHandItem.getEnchantmentLevel(serverLevel.registryAccess().holderOrThrow(Enchantments.UNBREAKING));
                if (enchantmentLevel != 0) {
                    double ran = ((double) 100 / (enchantmentLevel + 1)) / 100;
                    if (serverLevel.random.nextDouble() <= ran) {
                        mainHandItem.setDamageValue(
                                Math.min(mainHandItem.getMaxDamage(),
                                        mainHandItem.getDamageValue() +
                                                Math.max(1, value.getCookingTime() / 250)));
                    }
                } else {
                    mainHandItem.setDamageValue(
                            Math.min(mainHandItem.getMaxDamage(),
                                    mainHandItem.getDamageValue() +
                                            Math.max(1, value.getCookingTime() / 250)));
                }
                neoCount++;
            }
            resultItem.setCount(neoCount);
            Block.popResource(serverLevel, pos, resultItem);
            drop.setCount(count);
            Block.popResource(serverLevel, pos, drop);
        };
    }

    public static void autoSmelting(ServerLevel serverLevel, BlockPos pos, ItemStack mainHandItem, Player player) {
        if (player.isCreative())
            return;
        List<ItemStack> drops = Block.getDrops(serverLevel.getBlockState(pos), serverLevel, pos, serverLevel.getBlockEntity(pos), player, mainHandItem);
        for (ItemStack drop : drops) {

            RecipeManager recipeManager = serverLevel.getRecipeManager();
            recipeManager
                    .getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(drop), serverLevel)
                    .ifPresentOrElse(furnaceImpl(serverLevel, pos, mainHandItem, drop), () -> Block.popResource(serverLevel, pos, drop));
        }
    }

    public static boolean isTool(ItemStack stack, TagKey<Item> itemTag, TagKey<Block> blockTag, ServerLevel serverLevel, BlockPos pos) {
        return stack.is(itemTag) && serverLevel.getBlockState(pos).is(blockTag);
    }

    @SafeVarargs
    public static boolean nullTool(ServerLevel level, BlockPos pos, TagKey<Block>... blockTags) {
        BlockState state = level.getBlockState(pos);

        for (TagKey<Block> blockTag : blockTags) {
            if (state.is(blockTag)) {
                return false;
            }
        }
        return true;
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

    @SuppressWarnings("SameParameterValue")
    public static <T> void componentAdd(ItemStack stack, List<Component> toolTip, ModTranslateKey key, Supplier<DataComponentType<T>> data, T defaultT) {
        if (stack.has(data)) {
            toolTip.addLast(Component
                    .empty()
                    .append(key.translatable())
                    .append(String.valueOf(stack.getOrDefault(data, defaultT))));
        }
    }
}
