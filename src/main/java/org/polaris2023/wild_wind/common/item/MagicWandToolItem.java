package org.polaris2023.wild_wind.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.polaris2023.wild_wind.client.ModTranslateKey;

import java.util.LinkedHashMap;
import java.util.Map;

public class MagicWandToolItem extends Item {
    public MagicWandToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(!context.getLevel().isClientSide) {
            return InteractionResult.FAIL;
        }

        Level level = context.getLevel();
        Player player = context.getPlayer();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        Map<MutableComponent, String> MESSAGES = new LinkedHashMap<>();

        String burnTimeStr;
        try {
            int burnTime = clickedBlock.asItem().builtInRegistryHolder().getData(NeoForgeDataMaps.FURNACE_FUELS).burnTime();
            burnTimeStr = String.valueOf(burnTime);
        } catch (NullPointerException e) {
            burnTimeStr = "否";
        }

        String compostStr;
        try {
            float burnTime = clickedBlock.asItem().builtInRegistryHolder().getData(NeoForgeDataMaps.COMPOSTABLES).chance();
            compostStr = String.valueOf(burnTime);
        } catch (NullPointerException e) {
            compostStr = "否";
        }

        MESSAGES.put(ModTranslateKey.BLOCK_ID.translatable(), clickedBlock.getDescriptionId());
        MESSAGES.put(ModTranslateKey.MAX_STACK_SIZE.translatable(), String.valueOf(clickedBlock.asItem().getDefaultMaxStackSize()));
        MESSAGES.put(ModTranslateKey.HARDNESS.translatable(), String.valueOf(clickedBlock.defaultBlockState().getDestroySpeed(level, context.getClickedPos())));
        MESSAGES.put(ModTranslateKey.BLAST_RESISTANCE.translatable(), String.valueOf(clickedBlock.getExplosionResistance(clickedBlock.defaultBlockState(),
                level, context.getClickedPos(), new Explosion(level, null, 0, 0, 0, 0, false, Explosion.BlockInteraction.DESTROY))));
        MESSAGES.put(ModTranslateKey.FLAME_ODDS.translatable(), String.valueOf(clickedBlock.defaultBlockState().getFireSpreadSpeed(level, context.getClickedPos(), context.getClickedFace())));
        MESSAGES.put(ModTranslateKey.BURN_TIME.translatable(), String.valueOf(clickedBlock.defaultBlockState().getFlammability(level, context.getClickedPos(), context.getClickedFace())));
        MESSAGES.put(ModTranslateKey.LAVA_FLAMMABLE.translatable(), String.valueOf(clickedBlock.defaultBlockState().ignitedByLava()));
        MESSAGES.put(ModTranslateKey.SUFFOCATING_BLOCK.translatable(), String.valueOf(clickedBlock.defaultBlockState().isSuffocating(level, context.getClickedPos())));
        MESSAGES.put(ModTranslateKey.REDSTONE_CONDUCTING.translatable(), String.valueOf(clickedBlock.defaultBlockState().isRedstoneConductor(level, context.getClickedPos())));
        MESSAGES.put(ModTranslateKey.SOLID.translatable(), String.valueOf(clickedBlock.defaultBlockState().isSolid()));
        MESSAGES.put(ModTranslateKey.PUSH_REACTION.translatable(), String.valueOf(clickedBlock.defaultBlockState().getPistonPushReaction()));
        MESSAGES.put(ModTranslateKey.BLOCK_LIGHT.translatable(), String.valueOf(clickedBlock.getLightEmission(clickedBlock.defaultBlockState(), level, context.getClickedPos())));
        MESSAGES.put(ModTranslateKey.COMPOSTING_CHANCE.translatable(), compostStr);
        MESSAGES.put(ModTranslateKey.BURN_TIME.translatable(), burnTimeStr);

        if (player != null) {
            if(player.isShiftKeyDown()) {
                sendMessage(player, MESSAGES);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected void sendMessage(Player player, Map<MutableComponent, String> messages) {
        player.sendSystemMessage(Component.empty());
        for(Map.Entry<MutableComponent, String> entry : messages.entrySet()) {
            player.sendSystemMessage(
                    Component.empty()
                            .append(entry.getKey().withStyle(ChatFormatting.RED))
                            .append(Component.literal(entry.getValue()).withStyle(ChatFormatting.WHITE))
            );
        }
    }
}
