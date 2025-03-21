package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.ModBannerBlockEntity;

public class ModBlockEntityType {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, WildWindMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModBannerBlockEntity>> BANNER =
            BLOCK_ENTITIES.register("banner", () -> BlockEntityType.Builder.of(ModBannerBlockEntity::new, ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get()).build(null));

}
