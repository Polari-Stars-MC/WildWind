package org.polaris2023.wild_wind.common.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;

import org.polaris2023.annotation.modelgen.block.*;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.*;

import org.polaris2023.wild_wind.common.block.misc.AxeStrippableBlock;
import org.polaris2023.wild_wind.common.block.misc.ModBannerBlock;
import org.polaris2023.wild_wind.common.block.misc.ModWallBannerBlock;
import org.polaris2023.wild_wind.util.Helpers;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.ofFullCopy;
import static org.polaris2023.wild_wind.util.registry.ModBlockRegUtils.*;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildWindMod.MOD_ID);

    public static final DeferredHolder<Block, LiquidBlock> MILK_BLOCK = BLOCKS.register("milk", () -> new LiquidBlock(ModFluids.MILK.get(),
            BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().replaceable().strength(100.0F)
                    .pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    public static final BlockBehaviour.Properties BASE = BlockBehaviour.Properties.of();
    @I18n(en_us = "Glow Mucus", zh_cn = "萤光黏液", zh_tw = "螢光黏液")
    public static final DeferredBlock<Block> GLOW_MUCUS = BLOCKS.register("glow_mucus", () -> new GlowMucusBlock(BASE));
    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    public static final DeferredBlock<Block> FIREFLY_JAR = BLOCKS.registerSimpleBlock("firefly_jar", BASE.noLootTable());
    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    public static final DeferredBlock<Block> GLAREFLOWER = BLOCKS.registerSimpleBlock("glareflower");
    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = BLOCKS.registerSimpleBlock("glareflower_seeds");
    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredBlock<Block> SPIDER_EGG = BLOCKS.registerSimpleBlock("spider_egg", BASE.noLootTable());
    @I18n(en_us = "Spider Attachments", zh_cn = "蛛丝覆层", zh_tw = "蛛絲覆層")
    public static final DeferredBlock<Block> SPIDER_COVER = normal("spider_cover", BASE.noLootTable());
    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> SPIDER_MUCOSA = BLOCKS.registerSimpleBlock("spider_mucosa", BASE.noLootTable());
    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    public static final DeferredBlock<Block> REEDS = BLOCKS.registerSimpleBlock("reeds");
    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    public static final DeferredBlock<Block> CATTAILS = BLOCKS.register("cattails", () -> new CattailsBlock(ofFullCopy(Blocks.ROSE_BUSH)));
    @I18n(en_us = "Present", zh_cn = "礼物盒", zh_tw = "禮物盒")
    public static final DeferredBlock<PresentBlock> PRESENT = BLOCKS.register("present", () -> new PresentBlock(BASE.noLootTable()));
    @I18n(en_us = "Trapped Present", zh_cn = "陷阱礼物盒", zh_tw = "陷阱禮物盒")
    public static final DeferredBlock<TrappedPresentBlock> TRAPPED_PRESENT = BLOCKS.register("trapped_present", () -> new TrappedPresentBlock(BASE.noLootTable()));
    @I18n(en_us = "Bed", zh_cn = "床", zh_tw = "床")
    public static final DeferredBlock<Block> BED = register("bed", () -> new NeoBedBlock(ofFullCopy(Blocks.BLACK_BED)));
    @I18n(en_us = "Banner", zh_cn = "旗帜", zh_tw = "旗幟")
    public static final DeferredBlock<Block> BANNER = BLOCKS.register("banner",
            () -> new ModBannerBlock(13419950, ofFullCopy(Blocks.WHITE_BANNER)));
    public static final DeferredBlock<Block> WALL_BANNER = BLOCKS.register("wall_banner",
            () -> new ModWallBannerBlock(13419950, ofFullCopy(Blocks.WHITE_BANNER)));
    @I18n(en_us = "Silt", zh_cn = "淤泥", zh_tw = "淤泥")
    @BasicBlock
    public static final DeferredBlock<Block> SILT = register("silt", () -> new SiltBlock(
            ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f).sound(SoundType.MUD)
                    .isSuffocating((state, level, pos) -> true).pushReaction(PushReaction.DESTROY).randomTicks()));
    @I18n(en_us = "Tiny Cactus", zh_cn = "仙人球", zh_tw = "仙人球")
    public static final DeferredBlock<Block> TINY_CACTUS = BLOCKS.register("tiny_cactus", TinyCactusBlock::new);
    @I18n(en_us = "Quicksand", zh_cn = "流沙", zh_tw = "流沙")
    @BasicBlock
    public static final DeferredBlock<Block> QUICKSAND = register("quicksand", () -> new QuicksandBlock(
            ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f).sound(SoundType.SAND)
                    .isSuffocating((state, level, pos) -> true).pushReaction(PushReaction.DESTROY), Blocks.SAND.defaultBlockState()));
    @I18n(en_us = "Red Quicksand", zh_cn = "红沙流沙", zh_tw = "紅沙流沙")
    @BasicBlock
    public static final DeferredBlock<Block> RED_QUICKSAND = register("red_quicksand", () -> new QuicksandBlock(
            ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f).sound(SoundType.SAND).isSuffocating((state, level, pos) -> true)
                    .pushReaction(PushReaction.DESTROY), Blocks.RED_SAND.defaultBlockState()));
    @I18n(en_us = "Cooking Pot", zh_cn = "烹饪锅", zh_tw = "烹饪鍋具")
    public static final DeferredBlock<Block> COOKING_POT = BLOCKS.register("cooking_pot",
            () -> new CookingPotBlock(BASE.strength(2.0F, 6.0F)));
    @I18n(en_us = "Sculk Jaw", zh_cn = "幽匿颚口", zh_tw = "幽匿顎口")
    public static final DeferredBlock<Block> SCULK_JAW = BLOCKS.register("sculk_jaw", () -> new SculkJawBlock(BASE));
    @I18n(en_us = "Duckweed", zh_cn = "浮萍", zh_tw = "浮萍")
    public static final DeferredBlock<Block> DUCKWEED = BLOCKS.register("duckweed", () -> new DuckweedBlock(BASE));
    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    public static final DeferredBlock<Block> BRITTLE_ICE = BLOCKS.register("brittle_ice", () -> new BrittleIceBlock(
            ofFullCopy(Blocks.ICE).strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY)));
    @I18n(en_us = "Ash Block", zh_cn = "灰烬块", zh_tw = "灰烬块")
    @BasicBlock
    public static final DeferredBlock<Block> ASH_BLOCK = BLOCKS.registerSimpleBlock("ash_block", ofFullCopy(Blocks.SNOW_BLOCK));
    @I18n(en_us = "Ash", zh_cn = "灰烬", zh_tw = "灰烬")
    public static final DeferredBlock<Block> ASH = BLOCKS.register("ash", () -> new AshLayerBlock(ofFullCopy(Blocks.SNOW)));

    @I18n(en_us = "wool", zh_cn = "羊毛", zh_tw = "羊毛")
    @BasicBlock
    public static final DeferredBlock<Block> WOOL = normal("wool",
            BASE.instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava());

    @I18n(en_us = "carpet", zh_cn = "地毯", zh_tw = "地毯")
    public static final DeferredBlock<Block> CARPET = register("carpet",
            () -> new CarpetBlock(BASE.strength(0.1F).sound(SoundType.WOOL).ignitedByLava()));

    @I18n(en_us = "Concrete", zh_cn = "混凝土", zh_tw = "混凝土")
    @BasicBlock
    public static final DeferredBlock<Block> CONCRETE = normal("concrete",
            ofFullCopy(Blocks.WHITE_CONCRETE).strength(0.8F).ignitedByLava());
    @I18n(en_us = "Concrete Powder", zh_cn = "混凝土粉末", zh_tw = "混凝土粉末")
    @BasicBlock
    public static final DeferredBlock<Block> CONCRETE_POWDER = register("concrete_powder",
            () -> new ConcretePowderBlock(CONCRETE.get(), ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).ignitedByLava()));
    @I18n(en_us ="Glazed Terracotta", zh_cn = "带釉陶瓦", zh_tw = "带釉陶瓦")
    public static final DeferredBlock<Block> GLAZED_TERRACOTTA = register("glazed_terracotta",
            () -> new GlazedTerracottaBlock(ofFullCopy(Blocks.BLACK_GLAZED_TERRACOTTA)));

    @I18n(en_us = "Salt Block", zh_cn = "盐块", zh_tw = "鹽塊")
    @BasicBlock
    public static final DeferredBlock<Block> SALT_BLOCK = normal("salt_block",
            BASE.strength(3F).requiresCorrectToolForDrops().isRedstoneConductor((_0, _1, _2) -> true));

    @I18n(en_us = "Salt Ore", zh_cn = "盐矿石", zh_tw = "鹽礦石")
    @BasicBlock
    public static final DeferredBlock<Block> SALT_ORE = register("salt_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5),
            BASE.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3)));

    @I18n(en_us = "Deepslate Salt Ore", zh_cn = "深层盐矿石", zh_tw = "深層鹽礦石")
    @BasicBlock
    public static final DeferredBlock<Block> DEEPSLATE_SALT_ORE = register("deepslate_salt_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 5), BASE
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3)
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)));

    @I18n(en_us = "Glistering Melon", zh_cn = "闪烁的西瓜", zh_tw = "閃爍的西瓜")
    public static final DeferredBlock<Block> GLISTERING_MELON = BLOCKS.registerSimpleBlock("glistering_melon", BASE.mapColor(MapColor.GOLD));

    @I18n(en_us = "Stone Wall", zh_tw = "石牆", zh_cn = "石墙")
    @Wall(wall = "minecraft:block/stone")
    public static final DeferredBlock<WallBlock> STONE_WALL = wall("stone_wall", ofFullCopy(Blocks.STONE));

    @I18n(en_us = "Polished Stone",zh_cn = "磨制石头",zh_tw = "磨製石頭")
    @BasicBlock
    public static final DeferredBlock<Block> POLISHED_STONE = normal("polished_stone", BASE.mapColor(MapColor.STONE).strength(2.5f));
    @I18n(en_us = "Polished Stone Wall",zh_cn = "磨制石墙",zh_tw = "磨製石牆")
    @Wall
    public static final DeferredBlock<WallBlock> POLISHED_STONE_WALL = wall("polished_stone_wall", ofFullCopy(Blocks.STONE_BRICK_WALL));
    @I18n(en_us = "Polished Stone Stairs",zh_cn = "磨制石楼梯",zh_tw = "磨製石樓梯")
    @Stairs(type = "stone")
    public static final DeferredBlock<StairBlock> POLISHED_STONE_STAIRS = stair("polished_stone_stairs", POLISHED_STONE, ofFullCopy(Blocks.STONE_STAIRS));

    @I18n(en_us = "Polished Stone Slab",zh_cn = "磨制石台阶",zh_tw = "磨製石半磚")
    @Slab(type = "stone")
    public static final DeferredBlock<SlabBlock> POLISHED_STONE_SLAB = slab("polished_stone_slab", ofFullCopy(Blocks.STONE_SLAB));

    @AllWood
    @I18n(en_us = "Azalea Planks", zh_cn = "杜鹃木板", zh_tw = "杜鵑木材")
    public static final DeferredBlock<Block> AZALEA_PLANKS = normal("azalea_planks", ofFullCopy(Blocks.MANGROVE_PLANKS));
    @I18n(en_us = "Stripped Azalea Log", zh_cn = "去皮杜鹃原木", zh_tw = "剝皮杜鵑原木")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_LOG = wood("stripped_azalea_log", Blocks.STRIPPED_MANGROVE_LOG.defaultMapColor());
    @I18n(en_us = "Stripped Azalea Wood", zh_cn = "去皮杜鹃木", zh_tw = "剝皮杜鵑木塊")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_WOOD = wood("stripped_azalea_wood", Blocks.STRIPPED_MANGROVE_WOOD.defaultMapColor());
    @I18n(en_us = "Azalea Log", zh_cn = "杜鹃原木", zh_tw = "杜鵑原木")
    public static final DeferredBlock<AxeStrippableBlock<?>> AZALEA_LOG = wood("azalea_log", STRIPPED_AZALEA_LOG, Blocks.MANGROVE_LOG.defaultMapColor());
    @I18n(en_us = "Azalea Wood", zh_cn = "杜鹃木", zh_tw = "杜鵑木塊")
    public static final DeferredBlock<AxeStrippableBlock<?>> AZALEA_WOOD = wood("azalea_wood", STRIPPED_AZALEA_WOOD, Blocks.MANGROVE_WOOD.defaultMapColor());
    @I18n(en_us = "Azalea Button", zh_cn = "杜鹃木按钮", zh_tw = "杜鵑木按鈕")
    public static final DeferredBlock<ButtonBlock> AZALEA_BUTTON = button("azalea_button", Boolean.TRUE, ModWoodSettings.AZALEA.setType);
    @I18n(en_us = "Azalea Fence", zh_cn = "杜鹃木栅栏", zh_tw = "杜鵑木柵欄")
    public static final DeferredBlock<FenceBlock> AZALEA_FENCE = fence("azalea_fence", ofFullCopy(Blocks.MANGROVE_FENCE));
    @I18n(en_us = "Azalea Fence Gate", zh_cn = "杜鹃木栅栏门", zh_tw = "杜鵑木柵欄門")
    public static final DeferredBlock<FenceGateBlock> AZALEA_FENCE_GATE = fenceGate("azalea_fence_gate", ofFullCopy(Blocks.MANGROVE_FENCE_GATE), ModWoodSettings.AZALEA.woodType);
    @I18n(en_us = "Azalea Pressure Plate", zh_cn = "杜鹃木压力板", zh_tw = "杜鵑木压力板")
    public static final DeferredBlock<PressurePlateBlock> AZALEA_PRESSURE_PLATE = pressurePlate("azalea_pressure_plate", ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE), ModWoodSettings.AZALEA.setType);
    @I18n(en_us = "Azalea Slab", zh_cn = "杜鹃木台阶", zh_tw = "杜鵑木半磚")
    public static final DeferredBlock<SlabBlock> AZALEA_SLAB = slab("azalea_slab", ofFullCopy(Blocks.MANGROVE_SLAB));
    @I18n(en_us = "Azalea Stairs", zh_cn = "杜鹃木楼梯", zh_tw = "杜鵑木樓梯")
    public static final DeferredBlock<StairBlock> AZALEA_STAIRS = stair("azalea_stairs", AZALEA_PLANKS, ofFullCopy(Blocks.MANGROVE_STAIRS));
    @I18n(en_us = "Azalea Door", zh_cn = "杜鹃木门", zh_tw = "杜鵑木門")
    public static final DeferredBlock<DoorBlock> AZALEA_DOOR = door("azalea_door", ofFullCopy(Blocks.MANGROVE_DOOR), ModWoodSettings.AZALEA.setType);
    @I18n(en_us = "Azalea Trapdoor", zh_cn = "杜鹃木活板门", zh_tw = "杜鵑木地板門")
    public static final DeferredBlock<TrapDoorBlock> AZALEA_TRAPDOOR = trapdoor("azalea_trapdoor", ofFullCopy(Blocks.MANGROVE_TRAPDOOR), ModWoodSettings.AZALEA.setType);
    @I18n(en_us = "Azalea Sign", zh_cn = "杜鹃木告示牌", zh_tw = "杜鵑木告示牌")
    public static final DeferredBlock<StandingSignBlock> AZALEA_SIGN = BLOCKS.register("azalea_sign",
            props -> new StandingSignBlock(ModWoodSettings.AZALEA.woodType, ofFullCopy(Blocks.MANGROVE_SIGN)));
    @I18n(en_us = "Azalea Wall Sign", zh_cn = "墙上的杜鹃木告示牌", zh_tw = "牆上的杜鵑木告示牌", descriptionId = "block.wild_wind.azalea_wall_sign")
    public static final DeferredBlock<WallSignBlock> AZALEA_WALL_SIGN = BLOCKS.register("azalea_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.AZALEA.woodType, ofFullCopy(Blocks.MANGROVE_WALL_SIGN).lootFrom(AZALEA_SIGN)));
    @I18n(en_us = "Azalea Hanging Sign", zh_cn = "悬挂式杜鹃木告示牌", zh_tw = "懸挂式杜鵑木告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> AZALEA_HANGING_SIGN = BLOCKS.register("azalea_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.AZALEA.woodType, ofFullCopy(Blocks.MANGROVE_HANGING_SIGN)));
    @I18n(en_us = "Azalea Wall Hanging Sign", zh_cn = "墙上的杜鹃木悬挂式告示牌", zh_tw = "牆上的杜鵑木懸挂式告示牌", descriptionId = "block.wild_wind.azalea_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> AZALEA_WALL_HANGING_SIGN = BLOCKS.register("azalea_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.AZALEA.woodType, ofFullCopy(Blocks.MANGROVE_WALL_HANGING_SIGN).lootFrom(AZALEA_HANGING_SIGN)));

    @AllWood
    @I18n(en_us = "Palm Planks", zh_cn = "棕榈木板", zh_tw = "棕櫚木材")
    public static final DeferredBlock<Block> PALM_PLANKS = normal("palm_planks", ofFullCopy(Blocks.SPRUCE_PLANKS));
    @I18n(en_us = "Stripped Palm Log", zh_cn = "去皮棕榈原木", zh_tw = "剝皮棕櫚原木")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_LOG = wood("stripped_palm_log", Blocks.STRIPPED_SPRUCE_LOG.defaultMapColor());
    @I18n(en_us = "Stripped Palm Wood", zh_cn = "去皮棕榈木", zh_tw = "剝皮棕櫚木塊")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_WOOD = wood("stripped_palm_wood", Blocks.STRIPPED_SPRUCE_WOOD.defaultMapColor());
    @I18n(en_us = "Palm Log", zh_cn = "棕榈原木", zh_tw = "棕櫚原木")
    public static final DeferredBlock<AxeStrippableBlock<?>> PALM_LOG = wood("palm_log", STRIPPED_PALM_LOG, Blocks.SPRUCE_LOG.defaultMapColor());
    @I18n(en_us = "Palm Wood", zh_cn = "棕榈木", zh_tw = "棕櫚木塊")
    public static final DeferredBlock<AxeStrippableBlock<?>> PALM_WOOD = wood("palm_wood", STRIPPED_PALM_WOOD, Blocks.SPRUCE_WOOD.defaultMapColor());
    @I18n(en_us = "Palm Button", zh_cn = "棕榈木按钮", zh_tw = "棕櫚木按鈕")
    public static final DeferredBlock<ButtonBlock> PALM_BUTTON = button("palm_button", Boolean.TRUE, ModWoodSettings.PALM.setType);
    @I18n(en_us = "Palm Fence", zh_cn = "棕榈木栅栏", zh_tw = "棕櫚木柵欄")
    public static final DeferredBlock<FenceBlock> PALM_FENCE = fence("palm_fence", ofFullCopy(Blocks.SPRUCE_FENCE));
    @I18n(en_us = "Palm Fence Gate", zh_cn = "棕榈木栅栏门", zh_tw = "棕櫚木柵欄門")
    public static final DeferredBlock<FenceGateBlock> PALM_FENCE_GATE = fenceGate("palm_fence_gate", ofFullCopy(Blocks.SPRUCE_FENCE_GATE), ModWoodSettings.PALM.woodType);
    @I18n(en_us = "Palm Pressure Plate", zh_cn = "棕榈木压力板", zh_tw = "棕櫚木压力板")
    public static final DeferredBlock<PressurePlateBlock> PALM_PRESSURE_PLATE = pressurePlate("palm_pressure_plate", ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE), ModWoodSettings.PALM.setType);
    @I18n(en_us = "Palm Slab", zh_cn = "棕榈木台阶", zh_tw = "棕櫚木半磚")
    public static final DeferredBlock<SlabBlock> PALM_SLAB = slab("palm_slab", ofFullCopy(Blocks.SPRUCE_SLAB));
    @I18n(en_us = "Palm Stairs", zh_cn = "棕榈木楼梯", zh_tw = "棕櫚木樓梯")
    public static final DeferredBlock<StairBlock> PALM_STAIRS = stair("palm_stairs", PALM_PLANKS, ofFullCopy(Blocks.SPRUCE_STAIRS));
    @I18n(en_us = "Palm Door", zh_cn = "棕榈木门", zh_tw = "棕櫚木門")
    public static final DeferredBlock<DoorBlock> PALM_DOOR = door("palm_door", ofFullCopy(Blocks.SPRUCE_DOOR), ModWoodSettings.PALM.setType);
    @I18n(en_us = "Palm Trapdoor", zh_cn = "棕榈木活板门", zh_tw = "棕櫚木地板門")
    public static final DeferredBlock<TrapDoorBlock> PALM_TRAPDOOR = trapdoor("palm_trapdoor", ofFullCopy(Blocks.SPRUCE_TRAPDOOR), ModWoodSettings.PALM.setType);
    @I18n(en_us = "Palm Sign", zh_cn = "棕榈木告示牌", zh_tw = "棕櫚木告示牌")
    public static final DeferredBlock<StandingSignBlock> PALM_SIGN = BLOCKS.register("palm_sign",
            props -> new StandingSignBlock(ModWoodSettings.PALM.woodType, ofFullCopy(Blocks.SPRUCE_SIGN)));
    @I18n(en_us = "Palm Wall Sign", zh_cn = "墙上的棕榈木告示牌", zh_tw = "牆上的棕櫚木告示牌", descriptionId = "block.wild_wind.palm_wall_sign")
    public static final DeferredBlock<WallSignBlock> PALM_WALL_SIGN = BLOCKS.register("palm_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.PALM.woodType, ofFullCopy(Blocks.SPRUCE_WALL_SIGN).lootFrom(PALM_SIGN)));
    @I18n(en_us = "Palm Hanging Sign", zh_cn = "棕榈木悬挂式告示牌", zh_tw = "棕櫚木懸挂式告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> PALM_HANGING_SIGN = BLOCKS.register("palm_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.PALM.woodType, ofFullCopy(Blocks.SPRUCE_HANGING_SIGN)));
    @I18n(en_us = "Palm Wall Hanging Sign", zh_cn = "墙上的棕榈木悬挂式告示牌", zh_tw = "牆上的棕櫚木懸挂式告示牌", descriptionId = "block.wild_wind.palm_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> PALM_WALL_HANGING_SIGN = BLOCKS.register("palm_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.PALM.woodType, ofFullCopy(Blocks.SPRUCE_WALL_HANGING_SIGN).lootFrom(PALM_HANGING_SIGN)));

    @I18n(en_us = "Palm Crown", zh_cn = "棕榈树冠", zh_tw = "棕櫚樹冠")
    @BasicBlock
    public static final DeferredBlock<Block> PALM_CROWN = normal("palm_crown", ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));

    @AllWood
    @I18n(en_us = "Baobab Planks", zh_cn = "猴面包木板", zh_tw = "猴麵包木材")
    public static final DeferredBlock<Block> BAOBAB_PLANKS = normal("baobab_planks", ofFullCopy(Blocks.ACACIA_PLANKS));
    @I18n(en_us = "Stripped Baobab Log", zh_cn = "去皮猴面包原木", zh_tw = "剝皮猴麵包原木")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_LOG = wood("stripped_baobab_log", Blocks.STRIPPED_ACACIA_LOG.defaultMapColor());
    @I18n(en_us = "Stripped Baobab Wood", zh_cn = "去皮猴面包木", zh_tw = "剝皮猴麵包木塊")
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_WOOD = wood("stripped_baobab_wood", Blocks.STRIPPED_ACACIA_WOOD.defaultMapColor());
    @I18n(en_us = "Baobab Log", zh_cn = "猴面包原木", zh_tw = "猴麵包原木")
    public static final DeferredBlock<AxeStrippableBlock<?>> BAOBAB_LOG = wood("baobab_log", STRIPPED_BAOBAB_LOG, Blocks.ACACIA_LOG.defaultMapColor());
    @I18n(en_us = "Baobab Wood", zh_cn = "猴面包木", zh_tw = "猴麵包木塊")
    public static final DeferredBlock<AxeStrippableBlock<?>> BAOBAB_WOOD = wood("baobab_wood", STRIPPED_BAOBAB_WOOD, Blocks.ACACIA_WOOD.defaultMapColor());
    @I18n(en_us = "Baobab Button", zh_cn = "猴面包木按钮", zh_tw = "猴麵包木按鈕")
    public static final DeferredBlock<ButtonBlock> BAOBAB_BUTTON = button("baobab_button", Boolean.TRUE, ModWoodSettings.BAOBAB.setType);
    @I18n(en_us = "Baobab Fence", zh_cn = "猴面包木栅栏", zh_tw = "猴麵包木柵欄")
    public static final DeferredBlock<FenceBlock> BAOBAB_FENCE = fence("baobab_fence", ofFullCopy(Blocks.ACACIA_FENCE));
    @I18n(en_us = "Baobab Fence Gate", zh_cn = "猴面包木栅栏门", zh_tw = "猴麵包木柵欄門")
    public static final DeferredBlock<FenceGateBlock> BAOBAB_FENCE_GATE = fenceGate("baobab_fence_gate", ofFullCopy(Blocks.ACACIA_FENCE_GATE), ModWoodSettings.BAOBAB.woodType);
    @I18n(en_us = "Baobab Pressure Plate", zh_cn = "猴面包木压力板", zh_tw = "猴麵包木压力板")
    public static final DeferredBlock<PressurePlateBlock> BAOBAB_PRESSURE_PLATE = pressurePlate("baobab_pressure_plate", ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE), ModWoodSettings.BAOBAB.setType);
    @I18n(en_us = "Baobab Slab", zh_cn = "猴面包木台阶", zh_tw = "猴麵包木半磚")
    public static final DeferredBlock<SlabBlock> BAOBAB_SLAB = slab("baobab_slab", ofFullCopy(Blocks.ACACIA_SLAB));
    @I18n(en_us = "Baobab Stairs", zh_cn = "猴面包木楼梯", zh_tw = "猴麵包木樓梯")
    public static final DeferredBlock<StairBlock> BAOBAB_STAIRS = stair("baobab_stairs", BAOBAB_PLANKS, ofFullCopy(Blocks.ACACIA_STAIRS));
    @I18n(en_us = "Baobab Door", zh_cn = "猴面包木门", zh_tw = "猴麵包木門")
    public static final DeferredBlock<DoorBlock> BAOBAB_DOOR = door("baobab_door", ofFullCopy(Blocks.ACACIA_DOOR), ModWoodSettings.BAOBAB.setType);
    @I18n(en_us = "Baobab Trapdoor", zh_cn = "猴面包木活板门", zh_tw = "猴麵包木地板門")
    public static final DeferredBlock<TrapDoorBlock> BAOBAB_TRAPDOOR = trapdoor("baobab_trapdoor", ofFullCopy(Blocks.ACACIA_TRAPDOOR), ModWoodSettings.BAOBAB.setType);
    @I18n(en_us = "Baobab Sign", zh_cn = "猴面包木告示牌", zh_tw = "猴麵包木告示牌")
    public static final DeferredBlock<StandingSignBlock> BAOBAB_SIGN = BLOCKS.register("baobab_sign",
            props -> new StandingSignBlock(ModWoodSettings.BAOBAB.woodType, ofFullCopy(Blocks.ACACIA_SIGN)));
    @I18n(en_us = "Baobab Wall Sign", zh_cn = "墙上的猴面包木告示牌", zh_tw = "牆上的猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_sign")
    public static final DeferredBlock<WallSignBlock> BAOBAB_WALL_SIGN = BLOCKS.register("baobab_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.BAOBAB.woodType, ofFullCopy(Blocks.ACACIA_WALL_SIGN).lootFrom(BAOBAB_SIGN)));
    @I18n(en_us = "Baobab Hanging Sign", zh_cn = "悬挂式猴面包木告示牌", zh_tw = "懸挂式猴麵包木告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> BAOBAB_HANGING_SIGN = BLOCKS.register("baobab_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.BAOBAB.woodType, ofFullCopy(Blocks.ACACIA_HANGING_SIGN)));
    @I18n(en_us = "Baobab Wall Hanging Sign", zh_cn = "墙上的悬挂式猴面包木告示牌", zh_tw = "牆上的懸挂式猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> BAOBAB_WALL_HANGING_SIGN = BLOCKS.register("baobab_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.BAOBAB.woodType, ofFullCopy(Blocks.ACACIA_WALL_HANGING_SIGN).lootFrom(BAOBAB_HANGING_SIGN)));

    @I18n(en_us = "Palm Leaves", zh_cn = "棕榈树叶", zh_tw = "棕櫚樹葉")
    @BasicBlock
    public static final DeferredBlock<Block> PALM_LEAVES = register("palm_leaves", () -> Blocks.leaves(SoundType.GRASS));
    @I18n(en_us = "Baobab Leaves", zh_cn = "猴面包树叶", zh_tw = "猴麵包樹葉")
    @CubeAll(all = Helpers.BLOCK_PLACEHOLDER)
    public static final DeferredBlock<Block> BAOBAB_LEAVES = register("baobab_leaves", () -> Blocks.leaves(SoundType.GRASS));

    //TODO: TreeGrower
    @I18n(en_us = "Palm Sapling", zh_cn = "棕榈树苗", zh_tw = "棕櫚樹苗")
    public static final DeferredBlock<Block> PALM_SAPLING = BLOCKS.register("palm_sapling",
            () -> new SaplingBlock(ModTreeGrowers.PALM, ofFullCopy(Blocks.SPRUCE_SAPLING)));
    @I18n(en_us = "Baobab Sapling", zh_cn = "猴面包树苗", zh_tw = "猴麵包樹苗")
    public static final DeferredBlock<Block> BAOBAB_SAPLING = BLOCKS.register("baobab_sapling",
            () -> new SaplingBlock(ModTreeGrowers.BAOBAB, ofFullCopy(Blocks.ACACIA_SAPLING)));

    @AllBrick
    @I18n(en_us = "Andesite Bricks", zh_cn = "安山岩砖", zh_tw = "安山岩磚")
    public static final DeferredBlock<Block> ANDESITE_BRICKS = normal("andesite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Cracked Andesite Bricks", zh_cn = "裂纹安山岩砖", zh_tw = "裂紋安山岩磚")
    public static final DeferredBlock<Block> CRACKED_ANDESITE_BRICKS = normal("cracked_andesite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Stairs", zh_cn = "安山岩砖楼梯", zh_tw = "安山岩磚樓梯")
    public static final DeferredBlock<StairBlock> ANDESITE_BRICK_STAIRS = stair("andesite_brick_stairs", ANDESITE_BRICKS, ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Slab", zh_cn = "安山岩砖台阶", zh_tw = "安山岩磚臺階")
    public static final DeferredBlock<SlabBlock> ANDESITE_BRICK_SLAB = slab("andesite_brick_slab", ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Wall", zh_cn = "安山岩砖墙", zh_tw = "安山岩磚墻")
    public static final DeferredBlock<WallBlock> ANDESITE_BRICK_WALL = wall("andesite_brick_wall", ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.STONE));

    @AllBrick
    @I18n(en_us = "Diorite Bricks", zh_cn = "闪长岩砖", zh_tw = "閃長岩磚")
    public static final DeferredBlock<Block> DIORITE_BRICKS = normal("diorite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Cracked Diorite Bricks", zh_cn = "裂纹闪长岩砖", zh_tw = "裂紋閃長岩磚")
    public static final DeferredBlock<Block> CRACKED_DIORITE_BRICKS = normal("cracked_diorite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Stairs", zh_cn = "闪长岩砖楼梯", zh_tw = "閃長岩磚樓梯")
    public static final DeferredBlock<StairBlock> DIORITE_BRICK_STAIRS = stair("diorite_brick_stairs", DIORITE_BRICKS, ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Slab", zh_cn = "闪长岩砖台阶", zh_tw = "閃長岩磚臺階")
    public static final DeferredBlock<SlabBlock> DIORITE_BRICK_SLAB = slab("diorite_brick_slab", ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Wall", zh_cn = "闪长岩砖墙", zh_tw = "閃長岩磚墻")
    public static final DeferredBlock<WallBlock> DIORITE_BRICK_WALL = wall("diorite_brick_wall", ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.QUARTZ));

    @AllBrick
    @I18n(en_us = "Granite Bricks", zh_cn = "花岗岩砖", zh_tw = "花崗岩磚")
    public static final DeferredBlock<Block> GRANITE_BRICKS = normal("granite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Cracked Granite Bricks", zh_cn = "裂纹花岗岩砖", zh_tw = "裂紋花崗岩磚")
    public static final DeferredBlock<Block> CRACKED_GRANITE_BRICKS = normal("cracked_granite_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Stairs", zh_cn = "花岗岩砖楼梯", zh_tw = "花崗岩磚樓梯")
    public static final DeferredBlock<StairBlock> GRANITE_BRICK_STAIRS = stair("granite_brick_stairs", GRANITE_BRICKS, ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Slab", zh_cn = "花岗岩砖台阶", zh_tw = "花崗岩磚臺階")
    public static final DeferredBlock<SlabBlock> GRANITE_BRICK_SLAB = slab("granite_brick_slab", ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Wall", zh_cn = "花岗岩砖墙", zh_tw = "花崗岩磚墻")
    public static final DeferredBlock<WallBlock> GRANITE_BRICK_WALL = wall("granite_brick_wall", ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DIRT));

    @AllBrick
    @I18n(en_us = "Blue Ice Bricks", zh_cn = "蓝冰砖", zh_tw = "藍冰磚")
    public static final DeferredBlock<Block> BLUE_ICE_BRICKS = normal("blue_ice_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Cracked Blue Ice Bricks", zh_cn = "裂纹蓝冰砖", zh_tw = "裂紋藍冰磚")
    public static final DeferredBlock<Block> CRACKED_BLUE_ICE_BRICKS = normal("cracked_blue_ice_bricks", ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Stairs", zh_cn = "蓝冰砖楼梯", zh_tw = "藍冰磚樓梯")
    public static final DeferredBlock<StairBlock> BLUE_ICE_BRICK_STAIRS = stair("blue_ice_brick_stairs", BLUE_ICE_BRICKS, ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Slab", zh_cn = "蓝冰砖台阶", zh_tw = "藍冰磚臺階")
    public static final DeferredBlock<SlabBlock> BLUE_ICE_BRICK_SLAB = slab("blue_ice_brick_slab", ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Wall", zh_cn = "蓝冰砖墙", zh_tw = "藍冰磚墻")
    public static final DeferredBlock<WallBlock> BLUE_ICE_BRICK_WALL = wall("blue_ice_brick_wall", ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.ICE));

}