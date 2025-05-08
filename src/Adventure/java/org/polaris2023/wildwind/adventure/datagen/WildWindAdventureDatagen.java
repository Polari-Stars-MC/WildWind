package org.polaris2023.wildwind.adventure.datagen;

import net.minecraft.DetectedVersion;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris2023.wildwind.ModToml;

import java.util.Optional;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ModToml.WILD_WIND_ADVENTURE)
public class WildWindAdventureDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        gen.addProvider(true, new PackMetadataGenerator(output)
                .add(
                        PackMetadataSection.TYPE,
                        new PackMetadataSection(
                                Component.literal("Resources for Wild Wind: Adventure"),
                                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                                Optional.empty()
                        )
                ));
    }
}
