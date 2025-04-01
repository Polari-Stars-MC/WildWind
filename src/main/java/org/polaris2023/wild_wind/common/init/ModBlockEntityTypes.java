package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.DSL;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.DuckweedBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;

public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, WildWindMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModBannerBlockEntity>> BANNER_BE = BLOCK_ENTITY_TYPES.register("banner",
            () -> BlockEntityType.Builder.of((pos, blockState) -> new ModBannerBlockEntity(pos, blockState, 13419950),
                    ModBlocks.BANNER.get(), ModBlocks.WALL_BANNER.get()).build(DSL.remainderType()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT_TILE = BLOCK_ENTITY_TYPES.register("cooking_pot",
            () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, ModBlocks.COOKING_POT.get()).build(DSL.remainderType()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DuckweedBlockEntity>> DUCKWEED_TILE = BLOCK_ENTITY_TYPES.register("duckweed",
            () -> BlockEntityType.Builder.of(DuckweedBlockEntity::new, ModBlocks.DUCKWEED.get()).build(DSL.remainderType()));

}