package org.polaris2023.wild_wind.client;

import net.minecraft.util.FastColor;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.client.entity.abstracts.ModMobRenderer;
import org.polaris2023.wild_wind.client.entity.firefly.FireflyModel;
import org.polaris2023.wild_wind.client.entity.piranha.PiranhaModel;
import org.polaris2023.wild_wind.client.entity.trout.TroutModel;
import org.polaris2023.wild_wind.client.renderer.ModBannerRenderer;
import org.polaris2023.wild_wind.common.entity.layer.ModModelLayers;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModEntities;

@EventBusSubscriber(modid = WildWindMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WildWindClientEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerSlimeColor(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> FastColor.ARGB32.opaque(stack.getOrDefault(ModComponents.SLIME_COLOR, 0)), Items.SLIME_BALL);
        event.register((stack, tintIndex) -> FastColor.ARGB32.opaque(stack.getOrDefault(ModComponents.COLOR, 0)), ModBlocks.WOOL);
        event.register((stack, tintIndex) -> FastColor.ARGB32.opaque(13419950), ModBlocks.BANNER.asItem(), ModBlocks.WALL_BANNER.asItem());
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> FastColor.ARGB32.opaque(13419950), ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get());
//        event.register((state, world, pos, tintIndex) ->
//                        world != null && pos != null ? BiomeColors.getAverageGrassColor(
//                                world, state.getValue(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos
//                        ) : GrassColor.getDefaultColor(), ModBlocks.CATTAILS.get(), ModBlocks.REEDS.get());
    }

    @SubscribeEvent
    public static void registerRender(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlocks.BANNER_BE.get(), ModBannerRenderer::new);
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
        event.registerLayerDefinition(ModModelLayers.BANNER, ModBannerRenderer::createBodyLayer);
    }

}