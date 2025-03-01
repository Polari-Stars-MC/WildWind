package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;

import org.polaris2023.annotation.modelgen.block.*;
import org.polaris2023.annotation.modelgen.item.BasicBlockItem;
import org.polaris2023.annotation.modelgen.item.BasicBlockItemWithSuffix;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.block.*;

import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.DuckweedBlockEntity;

import java.util.Arrays;

import static org.polaris2023.wild_wind.common.init.ModInitializer.*;


public class ModBlocks {


    @I18n(en_us = "Glow Mucus", zh_cn = "萤光黏液", zh_tw = "螢光黏液")
    public static final DeferredBlock<GlowMucusBlock> GLOW_MUCUS = register("glow_mucus", GlowMucusBlock::new, BlockBehaviour.Properties.of());
    @BasicItem
    public static final DeferredItem<BlockItem> GLOW_MUCUS_ITEM =
            register("glow_mucus", GLOW_MUCUS);

    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");
    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_ITEM =
            register("glareflower", GLAREFLOWER);

    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");
    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_SEEDS_ITEM =
            register("glareflower_seeds", GLAREFLOWER_SEEDS, ModFoods.GLAREFLOWER_SEEDS);

    @I18n(en_us = "Spider Egg", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> SPIDER_EGG = register("spider_egg", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Spider Attachments", zh_cn = "蛛丝附层", zh_tw = "蛛絲附層")
    public static final DeferredBlock<Block> SPIDER_ATTACHMENTS = register("spider_attachments", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> SPIDER_MUCOSA = register("spider_mucosa", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    public static final DeferredBlock<Block> REEDS = register("reeds");
    @BasicItem
    public static final DeferredItem<BlockItem> REEDS_ITEM =
            register("reeds", REEDS);

    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    public static final DeferredBlock<Block> CATTAILS = register("cattails");
    @BasicItem
    public static final DeferredItem<BlockItem> CATTAILS_ITEM =
            register("cattails", CATTAILS);

    @I18n(en_us = "Present", zh_cn = "礼物盒", zh_tw = "禮物盒")
    public static final DeferredBlock<Block> PRESENT = register("present", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Trapped Present", zh_cn = "陷阱礼物盒", zh_tw = "陷阱禮物盒")
    public static final DeferredBlock<Block> TRAPPED_PRESENT =
            register("trapped_present", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cooking Pot", zh_cn = "烹饪锅", zh_tw = "烹饪鍋具")
    public static final DeferredBlock<CookingPotBlock> COOKING_POT =
            register("cooking_pot", CookingPotBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 6.0F));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT_TILE =
            entity("cooking_pot", DSL.remainderType(), CookingPotBlockEntity::new, COOKING_POT);
    public static final DeferredItem<BlockItem> COOKING_POT_ITEM =
            register("cooking_pot", COOKING_POT);

    @I18n(en_us = "Sculk Jaw", zh_cn = "幽匿厄口", zh_tw = "幽匿厄口")
    public static final DeferredBlock<SculkJawBlock> SCULK_JAW =
            register("sculk_jaw", SculkJawBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> SCULK_JAW_ITEM =
            register("sculk_jaw", SCULK_JAW);

    @I18n(en_us = "Duckweed", zh_cn = "浮萍", zh_tw = "浮萍")
    public static final DeferredBlock<DuckweedBlock> DUCKWEED =
            register("duckweed", DuckweedBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DuckweedBlockEntity>> DUCKWEED_TILE =
            entity("duckweed", DSL.remainderType(), DuckweedBlockEntity::new, DUCKWEED);
    public static final DeferredItem<BlockItem> DUCKWEED_ITEM =
            register("duckweed", DUCKWEED);

    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    public static final DeferredBlock<BrittleIceBlock> BRITTLE_ICE =
            register("brittle_ice", BrittleIceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> BRITTLE_ICE_ITEM =
            register("brittle_ice", BRITTLE_ICE);
  
    @I18n(en_us = "wool", zh_cn = "羊毛", zh_tw = "羊毛")
    public static final DeferredBlock<Block> WOOL = register("wool", BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.GUITAR)
            .strength(0.8F)
            .sound(SoundType.WOOL)
            .ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> WOOL_ITEM = register("wool", WOOL);

    @I18n(en_us = "carpet", zh_cn = "地毯", zh_tw = "地毯")
    public static final DeferredBlock<CarpetBlock> CARPET =
            register("carpet", CarpetBlock::new, BlockBehaviour.Properties.of().strength(0.1F).sound(SoundType.WOOL).ignitedByLava() );
    @BasicBlockItem
    public static final DeferredItem<BlockItem> CARPET_ITEM = register("carpet", CARPET);

    @I18n(en_us = "Concrete", zh_cn = "混凝土", zh_tw = "混凝土")
    public static final DeferredBlock<Block> CONCRETE =
            register("concrete", BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.STONE).ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> CONCRETE_ITEM = register("concrete", CONCRETE);
    @I18n(en_us ="Glazed Terracotta", zh_cn = "釉陶瓦", zh_tw = "釉陶瓦")
    public static final DeferredBlock<GlazedTerracottaBlock> GLAZED_TERRACOTTA =
            register("glazed_terracotta",  GlazedTerracottaBlock::new, BlockBehaviour.Properties.of().strength(1.25F).sound(SoundType.STONE).ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> GLAZED_TERRACOTTA_ITEM = register("glazed_terracotta", GLAZED_TERRACOTTA);

    @I18n(en_us = "Salt Block", zh_cn = "盐块", zh_tw = "鹽塊")
    public static final DeferredBlock<Block> SALT_BLOCK =
            register("salt_block", Block::new, BlockBehaviour.Properties.of()
                    .strength(3F)
                    .requiresCorrectToolForDrops()
                    .isRedstoneConductor((_0, _1, _2) -> true));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> SALT_BLOCK_ITEM =
            register("salt_block", SALT_BLOCK);
    @I18n(en_us = "Salt Ore", zh_cn = "盐矿石", zh_tw = "鹽礦石")
    public static final DeferredBlock<Block> SALT_ORE =
            register("salt_ore", p -> new DropExperienceBlock(UniformInt.of(2, 5), p), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> SALT_ORE_ITEM =
            register("salt_ore", SALT_ORE);

    @I18n(en_us = "Deepslate Salt Ore", zh_cn = "深层盐矿石", zh_tw = "深層鹽礦石")
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SALT_ORE =
            register("deepslate_salt_ore",
                    properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties), BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .strength(4.5F, 3)
                            .sound(SoundType.DEEPSLATE)
                            .mapColor(MapColor.DEEPSLATE));

    @BasicBlockItem
    public static final DeferredItem<BlockItem> DEEPSLATE_SALT_ORE_ITEM =
            register("deepslate_salt_ore", SALT_ORE);

    @I18n(en_us = "Azalea Log", zh_cn = "杜鹃木原木", zh_tw = "杜鵑木原木")
    public static final DeferredBlock<RotatedPillarBlock> AZALEA_LOG =
            register("azalea_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LOG));
    @I18n(en_us = "Stripped Azalea Log", zh_cn = "去皮杜鹃木原木", zh_tw = "剝皮杜鵑木原木")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_LOG =
            register("stripped_azalea_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG));
    @I18n(en_us = "Azalea Wood", zh_cn = "杜鹃木", zh_tw = "杜鵑木塊")

    public static final DeferredBlock<RotatedPillarBlock> AZALEA_WOOD =
            register("azalea_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD));
    @I18n(en_us = "Stripped Azalea Wood", zh_cn = "去皮杜鹃木", zh_tw = "剝皮杜鵑木塊")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_WOOD =
            register("stripped_azalea_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD));

    @I18n(en_us = "Azalea Planks", zh_cn = "杜鹃木木板", zh_tw = "杜鵑木材")
    @AllWood
    public static final DeferredBlock<Block> AZALEA_PLANKS =
            register("azalea_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    @I18n(en_us = "Azalea Button", zh_cn = "杜鹃木按钮", zh_tw = "杜鵑木按鈕")
    public static final DeferredBlock<ButtonBlock> AZALEA_BUTTON =
            register("azalea_button", props -> new ButtonBlock(ModBlockSetTypes.AZALEA, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_BUTTON));
    @I18n(en_us = "Azalea Fence", zh_cn = "杜鹃木栅栏", zh_tw = "杜鵑木柵欄")
    public static final DeferredBlock<FenceBlock> AZALEA_FENCE =
            register("azalea_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE));
    @I18n(en_us = "Azalea Fence Gate", zh_cn = "杜鹃木栅栏门", zh_tw = "杜鵑木柵欄門")
    public static final DeferredBlock<FenceGateBlock> AZALEA_FENCE_GATE =
            register("azalea_fence_gate", props -> new FenceGateBlock(ModWoodTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE));
    @I18n(en_us = "Azalea Pressure Plate", zh_cn = "杜鹃木压力板", zh_tw = "杜鵑木压力板")
    public static final DeferredBlock<PressurePlateBlock> AZALEA_PRESSURE_PLATE =
            register("azalea_pressure_plate", props -> new PressurePlateBlock(ModBlockSetTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE));
    @I18n(en_us = "Azalea Slab", zh_cn = "杜鹃木台阶", zh_tw = "杜鵑木半磚")
    public static final DeferredBlock<SlabBlock> AZALEA_SLAB =
            register("azalea_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
    @I18n(en_us = "Azalea Stairs", zh_cn = "杜鹃木楼梯", zh_tw = "杜鵑木樓梯")
    public static final DeferredBlock<StairBlock> AZALEA_STAIRS =
            register("azalea_stairs", props -> new StairBlock(AZALEA_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS));
    @I18n(en_us = "Azalea Door", zh_cn = "杜鹃木门", zh_tw = "杜鵑木門")
    public static final DeferredBlock<DoorBlock> AZALEA_DOOR =
            register("azalea_door", props -> new DoorBlock(ModBlockSetTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
    @I18n(en_us = "Azalea Trapdoor", zh_cn = "杜鹃木活板门", zh_tw = "杜鵑木地板門")
    public static final DeferredBlock<TrapDoorBlock> AZALEA_TRAPDOOR =
            register("azalea_trapdoor", props -> new TrapDoorBlock(ModBlockSetTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR));
    @I18n(en_us = "Azalea Sign", zh_cn = "杜鹃木告示牌", zh_tw = "杜鵑木告示牌")
    public static final DeferredBlock<StandingSignBlock> AZALEA_SIGN =
            register("azalea_sign", props -> new StandingSignBlock(ModWoodTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SIGN));
    @I18n(en_us = "Azalea Wall Sign", zh_cn = "墙上的杜鹃木告示牌", zh_tw = "牆上的杜鵑木告示牌")
    public static final DeferredBlock<WallSignBlock> AZALEA_WALL_SIGN =
            register("azalea_wall_sign", props -> new WallSignBlock(ModWoodTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_SIGN).lootFrom(AZALEA_SIGN));
    @I18n(en_us = "Azalea Hanging Sign", zh_cn = "杜鹃木悬挂式告示牌", zh_tw = "杜鵑木懸挂式告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> AZALEA_HANGING_SIGN =
            register("azalea_hanging_sign", props -> new CeilingHangingSignBlock(ModWoodTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_HANGING_SIGN));
    @I18n(en_us = "Azalea Wall Hanging Sign", zh_cn = "墙上的杜鹃木悬挂式告示牌", zh_tw = "牆上的杜鵑木懸挂式告示牌")
    public static final DeferredBlock<WallHangingSignBlock> AZALEA_WALL_HANGING_SIGN =
            register("azalea_wall_hanging_sign", props -> new WallHangingSignBlock(ModWoodTypes.AZALEA, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_HANGING_SIGN).lootFrom(AZALEA_HANGING_SIGN));
    public static final DeferredItem<BlockItem> AZALEA_LOG_ITEM =
            register("azalea_log", AZALEA_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_LOG_ITEM =
            register("stripped_azalea_log", STRIPPED_AZALEA_LOG);
    public static final DeferredItem<BlockItem> AZALEA_WOOD_ITEM =
            register("azalea_wood", AZALEA_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_WOOD_ITEM =
            register("stripped_azalea_wood", STRIPPED_AZALEA_WOOD);
    public static final DeferredItem<BlockItem> AZALEA_PLANKS_ITEM =
            register("azalea_planks", AZALEA_PLANKS);
    public static final DeferredItem<BlockItem> AZALEA_BUTTON_ITEM =
            register("azalea_button", AZALEA_BUTTON);
    public static final DeferredItem<BlockItem> AZALEA_FENCE_ITEM =
            register("azalea_fence", AZALEA_FENCE);
    public static final DeferredItem<BlockItem> AZALEA_FENCE_GATE_ITEM =
            register("azalea_fence_gate", AZALEA_FENCE_GATE);
    public static final DeferredItem<BlockItem> AZALEA_PRESSURE_PLATE_ITEM =
            register("azalea_pressure_plate", AZALEA_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> AZALEA_SLAB_ITEM =
            register("azalea_slab", AZALEA_SLAB);
    public static final DeferredItem<BlockItem> AZALEA_STAIRS_ITEM =
            register("azalea_stairs", AZALEA_STAIRS);

    public static final DeferredItem<BlockItem> AZALEA_DOOR_ITEM =
            register("azalea_door", AZALEA_DOOR);
    public static final DeferredItem<BlockItem> AZALEA_TRAPDOOR_ITEM =
            register("azalea_trapdoor", AZALEA_TRAPDOOR);
    public static final DeferredItem<SignItem> AZALEA_SIGN_ITEM =
            registerSign("azalea_sign", AZALEA_SIGN, AZALEA_WALL_SIGN);

    public static final DeferredItem<HangingSignItem> AZALEA_HANGING_SIGN_ITEM =
            registerHangingSign("azalea_hanging_sign", AZALEA_HANGING_SIGN, AZALEA_WALL_HANGING_SIGN);

    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>
    entity(String name,
             Type<?> type,
             BlockEntityType.BlockEntitySupplier<T> factory,
             DeferredBlock<?>... blocks) {

        return TILES.register(name, () -> BlockEntityType.Builder.of(factory, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)).build(type));
    }
}
