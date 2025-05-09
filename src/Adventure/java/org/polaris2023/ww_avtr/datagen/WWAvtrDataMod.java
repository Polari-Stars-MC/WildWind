package org.polaris2023.ww_avtr.datagen;

import net.minecraft.DetectedVersion;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import org.polaris2023.ww_avtr.utils.Helper;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Helper.MOD_ID)
public class WWAvtrDataMod {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        gen.addProvider(true, new PackMetadataGenerator(output)
                .add(
                        PackMetadataSection.TYPE,
                        new PackMetadataSection(
                                Component.literal("Resources for Wild Wind: Agricultural"),
                                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                                Optional.empty()
                        )
                ));
    }
}