package org.polaris_bear.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.polaris_bear.wild_wind.WildWindMod;
import org.polaris_bear.wild_wind.common.init.ModEntities;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        IntrinsicTagAppender<EntityType<?>> arthropod = tag(EntityTypeTags.ARTHROPOD);
        IntrinsicTagAppender<EntityType<?>> frogFood = tag(EntityTypeTags.FROG_FOOD);
        arthropod.add(ModEntities.FIREFLY.get());
        frogFood.add(ModEntities.FIREFLY.get());

    }


    protected IntrinsicTagAppender<EntityType<?>> tag(Supplier<TagKey<EntityType<?>>> tag) {
        return super.tag(tag.get());
    }
}
