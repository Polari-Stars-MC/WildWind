package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.tags.ModEntityTypeTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(EntityTypeTags.ARTHROPOD).add(ModEntities.FIREFLY.get());
        this.tag(EntityTypeTags.FROG_FOOD).add(ModEntities.FIREFLY.get());
        this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(EntityType.SQUID);
        this.tag(ModEntityTypeTags.WILD_WIND_INVISIBLE.get())
                .add(EntityType.ITEM_FRAME)
                .add(EntityType.GLOW_ITEM_FRAME);
    }


    protected IntrinsicTagAppender<EntityType<?>> tag(Supplier<TagKey<EntityType<?>>> tag) {
        return super.tag(tag.get());
    }
}
