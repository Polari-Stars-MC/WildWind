package org.polaris2023.wild_wind.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.client.entity.abstracts.ModMobRenderer;
import org.polaris2023.wild_wind.client.entity.firefly.FireflyModel;
import org.polaris2023.wild_wind.client.entity.piranha.PiranhaModel;
import org.polaris2023.wild_wind.client.entity.trout.TroutModel;
import org.polaris2023.wild_wind.common.block.WoolBlock;
import org.polaris2023.wild_wind.common.block.entity.WoolBlockEntity;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModEntities;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WildWindClientEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerSlimeColor(RegisterColorHandlersEvent.Item event) {

        event.register((stack, tintIndex) ->
                FastColor.ARGB32.opaque(stack.getOrDefault(ModComponents.SLIME_COLOR, 0)), Items.SLIME_BALL);
        event.register((stack, tintIndex) ->
                FastColor.ARGB32.opaque(stack.getOrDefault(ModComponents.COLOR, 0)), ModBlocks.WOOL_ITEM);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tinIndex) -> {
            if (level == null || pos == null) {
                return FastColor.ARGB32.opaque(0);
            }
            WoolBlockEntity woolTile = (WoolBlockEntity) level.getBlockEntity(pos);
            if (woolTile == null) return FastColor.ARGB32.opaque(0);
            return FastColor.ARGB32.opaque(woolTile.rgb);
        }, ModBlocks.WOOL.get());
    }

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.FIREFLY.get(), context ->
                new ModMobRenderer<>("firefly", context, FireflyModel::new, FireflyModel.LAYER_LOCATION, 1));
        event.registerEntityRenderer(ModEntities.TROUT.get(), context ->
                new ModMobRenderer<>("trout", context, TroutModel::new, TroutModel.LAYER_LOCATION, 1));
        event.registerEntityRenderer(ModEntities.PIRANHA.get(), context ->
                new ModMobRenderer<>("piranha", context, PiranhaModel::new, PiranhaModel.LAYER_LOCATION, 1));
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FireflyModel.LAYER_LOCATION, FireflyModel::createBodyLayer);
        event.registerLayerDefinition(TroutModel.LAYER_LOCATION, TroutModel::createBodyLayer);
        event.registerLayerDefinition(PiranhaModel.LAYER_LOCATION, PiranhaModel::createBodyLayer);
    }
}
