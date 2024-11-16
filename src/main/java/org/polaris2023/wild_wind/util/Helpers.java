package org.polaris2023.wild_wind.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.*;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.Collection;
import java.util.function.Supplier;

public final class Helpers {

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(WildWindMod.MOD_ID, path);
    }

    public static <T> TagKey<T> tags(ResourceKey<Registry<T>> resourceKey, String name) {
        return TagKey.create(resourceKey, location(name));
    }

    public static Collection<BlockState> assembleStates(Block block) {
        return block.getStateDefinition().getPossibleStates();
    }

    public static PoiType createPointOfInterest(Collection<BlockState> block) {
        return new PoiType(ImmutableSet.copyOf(block), 1, 1);
    }

    public static VillagerProfession createProf(ResourceLocation name, Supplier<ResourceKey<PoiType>> poi, SoundEvent sound) {
        ResourceKey<PoiType> poiName = poi.get();
        return new VillagerProfession(
                name.toString(),
                p -> p.is(poiName),
                p -> p.is(poiName),
                ImmutableSet.of(),
                ImmutableSet.of(),
                sound
        );
    }

}
