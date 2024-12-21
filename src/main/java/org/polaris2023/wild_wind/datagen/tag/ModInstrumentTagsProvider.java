package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.InstrumentTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Instrument;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModInstruments;
import org.polaris2023.wild_wind.common.init.tags.ModInstrumentTags;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModInstrumentTagsProvider extends InstrumentTagsProvider {

	public ModInstrumentTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, provider, WildWindMod.MOD_ID, existingFileHelper);
	}


	protected TagsProvider.TagAppender<Instrument> tag(Supplier<TagKey<Instrument>> tag) {
		return super.tag(tag.get());
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		TagsProvider.TagAppender<Instrument> magicFlute = tag(ModInstrumentTags.MAGIC_FLUTE);
		magicFlute.add(Objects.requireNonNull(ModInstruments.MAGIC_FLUTE.getKey()));
	}
}
