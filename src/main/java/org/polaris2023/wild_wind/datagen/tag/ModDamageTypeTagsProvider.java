package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModDamageType;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {
    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(output, lookupProvider, WildWindMod.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModDamageType.QUICKSAND_DAMAGE, DamageTypeTags.BYPASSES_ARMOR, DamageTypeTags.NO_KNOCKBACK);
        this.tag(ModDamageType.SILT_DAMAGE, DamageTypeTags.BYPASSES_ARMOR, DamageTypeTags.NO_KNOCKBACK);
        this.tag(ModDamageType.ICICLE_DAMAGE, DamageTypeTags.DAMAGES_HELMET, DamageTypeTags.BYPASSES_SHIELD);
        this.tag(ModDamageType.ICE_SPIKES_DAMAGE, DamageTypeTags.BYPASSES_ARMOR, DamageTypeTags.IS_FALL, DamageTypeTags.NO_KNOCKBACK);
        this.tag(ModDamageType.THORN_DAMAGE, DamageTypeTags.NO_KNOCKBACK);
    }

    @SafeVarargs
    private void tag(ResourceKey<DamageType> type, TagKey<DamageType>... tags) {
        for (TagKey<DamageType> key : tags) {
            tag(key).add(type);
        }
    }
}
