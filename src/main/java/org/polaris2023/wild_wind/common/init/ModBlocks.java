package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
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
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.language.I18n;

import org.polaris2023.annotation.modelgen.block.*;
import org.polaris2023.annotation.modelgen.item.*;
import org.polaris2023.annotation.register.RegistryBlockItem;
import org.polaris2023.annotation.handler.RegistryHandler;
import org.polaris2023.annotation.tag.CTag;
import org.polaris2023.annotation.tag.VanillaTag;
import org.polaris2023.annotation.tag.WildWindTag;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.*;

import org.polaris2023.wild_wind.common.block.entity.*;
import org.polaris2023.wild_wind.common.block.modified.*;

import java.util.Arrays;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.common.init.ModInitializer.*;
import static org.polaris2023.wild_wind.util.interfaces.registry.BlockRegistry.*;
import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.*;

@RegistryHandler(RegType.Block)
@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister.Blocks REGISTER =
            DeferredRegister.createBlocks(MOD_ID);

    public static final BlockBehaviour.Properties EMPTY = BlockBehaviour.Properties.of();
    @I18n(en_us = "Glow Mucus", zh_cn = "萤光黏液", zh_tw = "螢光黏液")
    public static final DeferredBlock<GlowMucusBlock> GLOW_MUCUS = register("glow_mucus", GlowMucusBlock::new, BlockBehaviour.Properties.of());

    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    @RegistryBlockItem
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar", BlockBehaviour.Properties.of().noLootTable());


    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");

    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");

    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredBlock<Block> SPIDER_EGG = register("spider_egg", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cobweb Cover", zh_cn = "蛛丝覆层", zh_tw = "蛛絲覆層")
    @RegistryBlockItem
    public static final DeferredBlock<Block> COBWEB_COVER = register("cobweb_cover", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cobweb Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> COBWEB_MUCOSA = register("cobweb_mucosa", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    public static final DeferredBlock<ReedsBlock> REEDS = register("reeds", ReedsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    public static final DeferredBlock<CattailsBlock> CATTAILS = register("cattails", CattailsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    @I18n(en_us = "Present", zh_cn = "礼物盒", zh_tw = "禮物盒")
    public static final DeferredBlock<PresentBlock> PRESENT = register("present", PresentBlock::new, BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Trapped Present", zh_cn = "陷阱礼物盒", zh_tw = "陷阱禮物盒")
    public static final DeferredBlock<TrappedPresentBlock> TRAPPED_PRESENT = register("trapped_present", TrappedPresentBlock::new, BlockBehaviour.Properties.of().noLootTable());


    @I18n(en_us = "Bed", zh_cn = "床", zh_tw = "床")
    @RegistryBlockItem
    @VanillaTag(names = "beds", type = TagType.Block)
    public static final DeferredBlock<NeoBedBlock> BED = register("bed", NeoBedBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_BED));

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, WildWindMod.MOD_ID);

    @I18n(en_us = "Banner", zh_cn = "旗帜", zh_tw = "旗幟")
    @VanillaTag(names = "banners", type = TagType.Block)
    @WildWindTag(names = "banners", type = TagType.Block)
    public static final DeferredBlock<ModBannerBlock> BANNER = register("banner", (p) -> new ModBannerBlock(13419950, p), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BANNER));
    @VanillaTag(names = "banners", type = TagType.Block)
    public static final DeferredBlock<ModWallBannerBlock> WALL_BANNER = register("wall_banner", (p) -> new ModWallBannerBlock(13419950, p), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BANNER));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModBannerBlockEntity>> BANNER_BE =
            entity("banner", DSL.remainderType(), (pos, blockState) -> new ModBannerBlockEntity(pos, blockState, 13419950), BANNER, WALL_BANNER);

    @I18n(en_us = "Silt", zh_cn = "淤泥", zh_tw = "淤泥")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = {"convertable_to_mud", "moss_replaceable", "azalea_grows_on", "azalea_root_replaceable", "mineable/shovel"}, type = TagType.Block)
    public static final DeferredBlock<Block> SILT = register("silt", SiltBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f, 0.35f)
                    .sound(SoundType.MUD)
                    .isSuffocating((state, level, pos) -> true)
                    .pushReaction(PushReaction.DESTROY)
                    .randomTicks());

    @Cross(item = false)
    @I18n(en_us = "Tiny Cactus", zh_cn = "仙人球", zh_tw = "仙人球")
    public static final DeferredBlock<FlowerBlock> TINY_CACTUS = register("tiny_cactus", TinyCactusBlock::new, BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Quicksand", zh_cn = "流沙", zh_tw = "流沙")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = "mineable/shovel", type = TagType.Block)
    public static final DeferredBlock<QuicksandBlock> QUICKSAND = register("quicksand", p -> new QuicksandBlock(p, Blocks.SAND.defaultBlockState()),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f, 0.35f)
                    .sound(SoundType.SAND)
                    .isSuffocating((state, level, pos) -> true)
                    .pushReaction(PushReaction.DESTROY));


    @I18n(en_us = "Red Quicksand", zh_cn = "红沙流沙", zh_tw = "紅沙流沙")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = "mineable/shovel", type = TagType.Block)
    public static final DeferredBlock<QuicksandBlock> RED_QUICKSAND = register("red_quicksand", p -> new QuicksandBlock(p, Blocks.RED_SAND.defaultBlockState()),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POWDER_SNOW).strength(0.35f).sound(SoundType.SAND).isSuffocating((state, level, pos) -> true).pushReaction(PushReaction.DESTROY));

    @I18n(en_us = "Cooking Pot", zh_cn = "烹饪锅", zh_tw = "烹饪鍋具")
    @RegistryBlockItem
    public static final DeferredBlock<CookingPotBlock> COOKING_POT = register("cooking_pot", CookingPotBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 6.0F));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT_TILE =
            entity("cooking_pot", DSL.remainderType(), CookingPotBlockEntity::new, COOKING_POT);

    @I18n(en_us = "Sculk Jaw", zh_cn = "幽匿颚口", zh_tw = "幽匿顎口")
    @RegistryBlockItem
    public static final DeferredBlock<SculkJawBlock> SCULK_JAW = register("sculk_jaw", SculkJawBlock::new, BlockBehaviour.Properties.of());

    @I18n(en_us = "Duckweed", zh_cn = "浮萍", zh_tw = "浮萍")

    public static final DeferredBlock<DuckweedBlock> DUCKWEED = register("duckweed", DuckweedBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DuckweedBlockEntity>> DUCKWEED_TILE =
            entity("duckweed", DSL.remainderType(), DuckweedBlockEntity::new, DUCKWEED);

    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    @CubeAllFor(
            cube = @CubeAll(render_type = "translucent", all = "wild_wind:block/brittle_ice_0"),
            min = 0,
            max = 3,
            def = "wild_wind:block/brittle_ice"
    ) @RegistryBlockItem
    @VanillaTag(names = {"snow_layer_cannot_survive_on", "mineable/pickaxe"}, type = TagType.Block)
    @CTag(names = "ice_skip", type = TagType.Block)
    public static final DeferredBlock<BrittleIceBlock> BRITTLE_ICE = register("brittle_ice", BrittleIceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));

    @I18n(en_us = "Ash Block", zh_cn = "灰烬块", zh_tw = "灰烬块")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = "mineable/shovel", type = TagType.Block)
    public static final DeferredBlock<Block> ASH_BLOCK = register("ash_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));


    @I18n(en_us = "Ash", zh_cn = "灰烬", zh_tw = "灰烬")
    @VanillaTag(names = {"replaceable", "mineable/shovel"}, type = TagType.Block)
    public static final DeferredBlock<AshLayerBlock> ASH = register("ash", AshLayerBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW));

  
    @I18n(en_us = "wool", zh_cn = "羊毛", zh_tw = "羊毛")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = "wool", type = TagType.Block)
    public static final DeferredBlock<Block> WOOL = register("wool", BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.GUITAR)
            .strength(0.8F)
            .sound(SoundType.WOOL)
            .ignitedByLava());


    @I18n(en_us = "carpet", zh_cn = "地毯", zh_tw = "地毯")
    @Carpet(carpet = "wild_wind:block/wool")
    @RegistryBlockItem
    @VanillaTag(names = "wool_carpets", type = TagType.Block)
    public static final DeferredBlock<CarpetBlock> CARPET = register("carpet", CarpetBlock::new, BlockBehaviour.Properties.of().strength(0.1F).sound(SoundType.WOOL).ignitedByLava() );


    @I18n(en_us = "Concrete", zh_cn = "混凝土", zh_tw = "混凝土")
    @BasicBlock @RegistryBlockItem
    @CTag(names = "concretes", type = TagType.Block)
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CONCRETE = register("concrete", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).strength(0.8F).ignitedByLava());

    @I18n(en_us = "Concrete Powder", zh_cn = "混凝土粉末", zh_tw = "混凝土粉末")
    @BasicBlock
    @CTag(names = "concrete_powders", type = TagType.Block)
    @VanillaTag(names = {"mineable/shovel", "camel_sand_step_sound_blocks"}, type = TagType.Block)
    public static final DeferredBlock<Block> CONCRETE_POWDER = register("concrete_powder", properties -> new ConcretePowderBlock(CONCRETE.get(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).ignitedByLava());
    public static final DeferredItem<BlockItem> CONCRETE_POWDER_ITEM = register("concrete_powder", CONCRETE_POWDER);
    @I18n(en_us ="Glazed Terracotta", zh_cn = "带釉陶瓦", zh_tw = "带釉陶瓦")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CTag(names = "glazed_terracottas", type = TagType.Block)
    public static final DeferredBlock<GlazedTerracottaBlock> GLAZED_TERRACOTTA = register("glazed_terracotta",  GlazedTerracottaBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_GLAZED_TERRACOTTA));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> GLAZED_TERRACOTTA_ITEM = register("glazed_terracotta", GLAZED_TERRACOTTA);

    @I18n(en_us = "Salt Block", zh_cn = "盐块", zh_tw = "鹽塊")
    @BasicBlock
    public static final DeferredBlock<Block> SALT_BLOCK = register("salt_block", Block::new, BlockBehaviour.Properties.of()
                    .strength(3F)
                    .requiresCorrectToolForDrops()
                    .isRedstoneConductor((_0, _1, _2) -> true));

    public static final DeferredItem<BlockItem> SALT_BLOCK_ITEM = register("salt_block", SALT_BLOCK);
    @I18n(en_us = "Salt Ore", zh_cn = "盐矿石", zh_tw = "鹽礦石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> SALT_ORE = register("salt_ore", p -> new DropExperienceBlock(UniformInt.of(2, 5), p), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3));
    public static final DeferredItem<BlockItem> SALT_ORE_ITEM = register("salt_ore", SALT_ORE);

    @I18n(en_us = "Deepslate Salt Ore", zh_cn = "深层盐矿石", zh_tw = "深層鹽礦石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SALT_ORE = register("deepslate_salt_ore",
                    properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties), BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .strength(4.5F, 3)
                            .sound(SoundType.DEEPSLATE)
                            .mapColor(MapColor.DEEPSLATE));

    public static final DeferredItem<BlockItem> DEEPSLATE_SALT_ORE_ITEM = register("deepslate_salt_ore", DEEPSLATE_SALT_ORE);
    @CubeBottomTop(side = "wild_wind:block/glistering_melon_side",
            bottom = "wild_wind:block/glistering_melon_side",
            top = "wild_wind:block/glistering_melon_top")
    @I18n(en_us = "Glistering Melon", zh_cn = "闪烁的西瓜", zh_tw = "閃爍的西瓜")
    @VanillaTag(names = {"sword_efficient", "enderman_holdable", "mineable/axe"}, type = TagType.Block)
    public static final DeferredBlock<Block> GLISTERING_MELON = register("glistering_melon", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD));

    @BasicBlockItem
    public static final DeferredItem<BlockItem> GLISTERING_MELON_ITEM = register("glistering_melon", GLISTERING_MELON);

    @I18n(en_us = "Stone Wall", zh_tw = "石牆", zh_cn = "石墙")
    @Wall(wall = "minecraft:block/stone")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> STONE_WALL = register("stone_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredItem<BlockItem> STONE_WALL_ITEM = register("stone_wall", STONE_WALL);

    @I18n(en_us = "Polished Stone",zh_cn = "磨制石头",zh_tw = "磨製石頭")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> POLISHED_STONE = register("polished_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.5f));
    public static final DeferredItem<BlockItem> POLISHED_STONE_ITEM = register("polished_stone", POLISHED_STONE);
    @I18n(en_us = "Polished Stone Wall",zh_cn = "磨制石墙",zh_tw = "磨製石牆")
    @Wall(wall = "wild_wind:block/polished_stone")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> POLISHED_STONE_WALL = register("polished_stone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.POLISHED_STONE.get())), EMPTY);
    public static final DeferredItem<BlockItem> POLISHED_STONE_WALL_ITEM = register("polished_stone_wall", POLISHED_STONE_WALL);
    @I18n(en_us = "Polished Stone Stairs",zh_cn = "磨制石楼梯",zh_tw = "磨製石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_stone", top = "wild_wind:block/polished_stone", side = "wild_wind:block/polished_stone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<StairBlock> POLISHED_STONE_STAIRS = register("polished_stone_stairs", properties -> new StairBlock(POLISHED_STONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredItem<BlockItem> POLISHED_STONE_STAIRS_ITEM = register("polished_stone_stairs", POLISHED_STONE_STAIRS);

    @I18n(en_us = "Polished Stone Slab",zh_cn = "磨制石台阶",zh_tw = "磨製石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_stone", side = "wild_wind:block/polished_stone", top = "wild_wind:block/polished_stone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<SlabBlock> POLISHED_STONE_SLAB = register("polished_stone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredItem<BlockItem> POLISHED_STONE_SLAB_ITEM = register("polished_stone_slab", POLISHED_STONE_SLAB);

    @AllWood
    @I18n(en_us = "Azalea Planks", zh_cn = "杜鹃木板", zh_tw = "杜鵑木材")
    public static final DeferredBlock<Block> AZALEA_PLANKS = register("azalea_planks",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    @I18n(en_us = "Azalea Log", zh_cn = "杜鹃原木", zh_tw = "杜鵑原木")
    @CTag(names = "azalea_logs", type = TagType.Block)
    public static final DeferredBlock<StrippableLog> AZALEA_LOG = register("azalea_log",
            p -> new StrippableLog(p, ModBlocks.STRIPPED_AZALEA_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LOG));
    @I18n(en_us = "Stripped Azalea Log", zh_cn = "去皮杜鹃原木", zh_tw = "剝皮杜鵑原木")
    @CTag(names = "azalea_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_LOG = register("stripped_azalea_log",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG));
    @I18n(en_us = "Azalea Wood", zh_cn = "杜鹃木", zh_tw = "杜鵑木塊")
    @CTag(names = "azalea_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> AZALEA_WOOD = register("azalea_wood",
            p -> new StrippableLog(p, ModBlocks.STRIPPED_AZALEA_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD));
    @I18n(en_us = "Stripped Azalea Wood", zh_cn = "去皮杜鹃木", zh_tw = "剝皮杜鵑木塊")
    @CTag(names = "azalea_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_WOOD = register("stripped_azalea_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD));
    @I18n(en_us = "Azalea Button", zh_cn = "杜鹃木按钮", zh_tw = "杜鵑木按鈕")
    public static final DeferredBlock<ButtonBlock> AZALEA_BUTTON = register("azalea_button",
            props -> new ButtonBlock(ModWoodSettings.AZALEA.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_BUTTON));
    @I18n(en_us = "Azalea Fence", zh_cn = "杜鹃木栅栏", zh_tw = "杜鵑木柵欄")
    public static final DeferredBlock<FenceBlock> AZALEA_FENCE = register("azalea_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE));
    @I18n(en_us = "Azalea Fence Gate", zh_cn = "杜鹃木栅栏门", zh_tw = "杜鵑木柵欄門")
    public static final DeferredBlock<FenceGateBlock> AZALEA_FENCE_GATE = register("azalea_fence_gate",
            props -> new FenceGateBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE));
    @I18n(en_us = "Azalea Pressure Plate", zh_cn = "杜鹃木压力板", zh_tw = "杜鵑木压力板")
    public static final DeferredBlock<PressurePlateBlock> AZALEA_PRESSURE_PLATE = register("azalea_pressure_plate",
            props -> new PressurePlateBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE));
    @I18n(en_us = "Azalea Slab", zh_cn = "杜鹃木台阶", zh_tw = "杜鵑木半磚")
    public static final DeferredBlock<SlabBlock> AZALEA_SLAB = register("azalea_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
    @I18n(en_us = "Azalea Stairs", zh_cn = "杜鹃木楼梯", zh_tw = "杜鵑木樓梯")
    public static final DeferredBlock<StairBlock> AZALEA_STAIRS = register("azalea_stairs",
            props -> new StairBlock(AZALEA_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS));
    @I18n(en_us = "Azalea Door", zh_cn = "杜鹃木门", zh_tw = "杜鵑木門")
    public static final DeferredBlock<DoorBlock> AZALEA_DOOR = register("azalea_door",
            props -> new DoorBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
    @I18n(en_us = "Azalea Trapdoor", zh_cn = "杜鹃木活板门", zh_tw = "杜鵑木地板門")
    public static final DeferredBlock<TrapDoorBlock> AZALEA_TRAPDOOR = register("azalea_trapdoor",
            props -> new TrapDoorBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR));
    @I18n(en_us = "Azalea Sign", zh_cn = "杜鹃木告示牌", zh_tw = "杜鵑木告示牌")
    public static final DeferredBlock<StandingSignBlock> AZALEA_SIGN = register("azalea_sign",
            props -> new StandingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SIGN));
    @I18n(en_us = "Azalea Wall Sign", zh_cn = "墙上的杜鹃木告示牌", zh_tw = "牆上的杜鵑木告示牌", descriptionId = "block.wild_wind.azalea_wall_sign")
    public static final DeferredBlock<WallSignBlock> AZALEA_WALL_SIGN = register("azalea_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_SIGN).lootFrom(AZALEA_SIGN));
    @I18n(en_us = "Azalea Hanging Sign", zh_cn = "悬挂式杜鹃木告示牌", zh_tw = "懸挂式杜鵑木告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> AZALEA_HANGING_SIGN = register("azalea_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_HANGING_SIGN));
    @I18n(en_us = "Azalea Wall Hanging Sign", zh_cn = "墙上的杜鹃木悬挂式告示牌", zh_tw = "牆上的杜鵑木懸挂式告示牌", descriptionId = "block.wild_wind.azalea_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> AZALEA_WALL_HANGING_SIGN = register("azalea_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_HANGING_SIGN).lootFrom(AZALEA_HANGING_SIGN));
    public static final DeferredItem<BlockItem> AZALEA_LOG_ITEM = register("azalea_log", AZALEA_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_LOG_ITEM = register("stripped_azalea_log", STRIPPED_AZALEA_LOG);
    public static final DeferredItem<BlockItem> AZALEA_WOOD_ITEM = register("azalea_wood", AZALEA_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_WOOD_ITEM = register("stripped_azalea_wood", STRIPPED_AZALEA_WOOD);
    public static final DeferredItem<BlockItem> AZALEA_PLANKS_ITEM = register("azalea_planks", AZALEA_PLANKS);
    public static final DeferredItem<BlockItem> AZALEA_BUTTON_ITEM = register("azalea_button", AZALEA_BUTTON);
    public static final DeferredItem<BlockItem> AZALEA_FENCE_ITEM = register("azalea_fence", AZALEA_FENCE);
    public static final DeferredItem<BlockItem> AZALEA_FENCE_GATE_ITEM = register("azalea_fence_gate", AZALEA_FENCE_GATE);
    public static final DeferredItem<BlockItem> AZALEA_PRESSURE_PLATE_ITEM = register("azalea_pressure_plate", AZALEA_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> AZALEA_SLAB_ITEM = register("azalea_slab", AZALEA_SLAB);
    public static final DeferredItem<BlockItem> AZALEA_STAIRS_ITEM = register("azalea_stairs", AZALEA_STAIRS);
    public static final DeferredItem<BlockItem> AZALEA_DOOR_ITEM = register("azalea_door", AZALEA_DOOR);
    public static final DeferredItem<BlockItem> AZALEA_TRAPDOOR_ITEM = register("azalea_trapdoor", AZALEA_TRAPDOOR);
    public static final DeferredItem<SignItem> AZALEA_SIGN_ITEM =
            registerSign("azalea_sign", AZALEA_SIGN, AZALEA_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> AZALEA_HANGING_SIGN_ITEM =
            registerHangingSign("azalea_hanging_sign", AZALEA_HANGING_SIGN, AZALEA_WALL_HANGING_SIGN);

    @AllWood
    @I18n(en_us = "Palm Planks", zh_cn = "棕榈木板", zh_tw = "棕櫚木材")
    public static final DeferredBlock<Block> PALM_PLANKS = register("palm_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    @I18n(en_us = "Palm Log", zh_cn = "棕榈原木", zh_tw = "棕櫚原木")
    @CTag(names = "palm_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> PALM_LOG = register("palm_log", p -> new StrippableLog(p, ModBlocks.STRIPPED_PALM_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
    @I18n(en_us = "Stripped Palm Log", zh_cn = "去皮棕榈原木", zh_tw = "剝皮棕櫚原木")
    @CTag(names = "palm_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_LOG = register("stripped_palm_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG));
    @I18n(en_us = "Palm Wood", zh_cn = "棕榈木", zh_tw = "棕櫚木塊")
    @CTag(names = "palm_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> PALM_WOOD = register("palm_wood", p -> new StrippableLog(p, ModBlocks.STRIPPED_PALM_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD));
    @I18n(en_us = "Stripped Palm Wood", zh_cn = "去皮棕榈木", zh_tw = "剝皮棕櫚木塊")
    @CTag(names = "palm_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_WOOD = register("stripped_palm_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));
    @I18n(en_us = "Palm Button", zh_cn = "棕榈木按钮", zh_tw = "棕櫚木按鈕")
    public static final DeferredBlock<ButtonBlock> PALM_BUTTON = register("palm_button", props -> new ButtonBlock(ModWoodSettings.PALM.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_BUTTON));
    @I18n(en_us = "Palm Fence", zh_cn = "棕榈木栅栏", zh_tw = "棕櫚木柵欄")
    public static final DeferredBlock<FenceBlock> PALM_FENCE = register("palm_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE));
    @I18n(en_us = "Palm Fence Gate", zh_cn = "棕榈木栅栏门", zh_tw = "棕櫚木柵欄門")
    public static final DeferredBlock<FenceGateBlock> PALM_FENCE_GATE = register("palm_fence_gate", props -> new FenceGateBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    @I18n(en_us = "Palm Pressure Plate", zh_cn = "棕榈木压力板", zh_tw = "棕櫚木压力板")
    public static final DeferredBlock<PressurePlateBlock> PALM_PRESSURE_PLATE = register("palm_pressure_plate", props -> new PressurePlateBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE));
    @I18n(en_us = "Palm Slab", zh_cn = "棕榈木台阶", zh_tw = "棕櫚木半磚")
    public static final DeferredBlock<SlabBlock> PALM_SLAB = register("palm_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB));
    @I18n(en_us = "Palm Stairs", zh_cn = "棕榈木楼梯", zh_tw = "棕櫚木樓梯")
    public static final DeferredBlock<StairBlock> PALM_STAIRS = register("palm_stairs", props -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    @I18n(en_us = "Palm Door", zh_cn = "棕榈木门", zh_tw = "棕櫚木門")
    public static final DeferredBlock<DoorBlock> PALM_DOOR = register("palm_door", props -> new DoorBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
    @I18n(en_us = "Palm Trapdoor", zh_cn = "棕榈木活板门", zh_tw = "棕櫚木地板門")
    public static final DeferredBlock<TrapDoorBlock> PALM_TRAPDOOR = register("palm_trapdoor", props -> new TrapDoorBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));
    @I18n(en_us = "Palm Sign", zh_cn = "棕榈木告示牌", zh_tw = "棕櫚木告示牌")
    public static final DeferredBlock<StandingSignBlock> PALM_SIGN = register("palm_sign", props -> new StandingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
    @I18n(en_us = "Palm Wall Sign", zh_cn = "墙上的棕榈木告示牌", zh_tw = "牆上的棕櫚木告示牌", descriptionId = "block.wild_wind.palm_wall_sign")
    public static final DeferredBlock<WallSignBlock> PALM_WALL_SIGN = register("palm_wall_sign", props -> new WallSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_SIGN).lootFrom(PALM_SIGN));
    @I18n(en_us = "Palm Hanging Sign", zh_cn = "棕榈木悬挂式告示牌", zh_tw = "棕櫚木懸挂式告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> PALM_HANGING_SIGN = register("palm_hanging_sign", props -> new CeilingHangingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN));
    @I18n(en_us = "Palm Wall Hanging Sign", zh_cn = "墙上的棕榈木悬挂式告示牌", zh_tw = "牆上的棕櫚木懸挂式告示牌", descriptionId = "block.wild_wind.palm_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> PALM_WALL_HANGING_SIGN = register("palm_wall_hanging_sign", props -> new WallHangingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_HANGING_SIGN).lootFrom(PALM_HANGING_SIGN));
    public static final DeferredItem<BlockItem> PALM_LOG_ITEM = register("palm_log", PALM_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_PALM_LOG_ITEM = register("stripped_palm_log", STRIPPED_PALM_LOG);
    public static final DeferredItem<BlockItem> PALM_WOOD_ITEM = register("palm_wood", PALM_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_PALM_WOOD_ITEM = register("stripped_palm_wood", STRIPPED_PALM_WOOD);
    public static final DeferredItem<BlockItem> PALM_PLANKS_ITEM = register("palm_planks", PALM_PLANKS);
    public static final DeferredItem<BlockItem> PALM_BUTTON_ITEM = register("palm_button", PALM_BUTTON);
    public static final DeferredItem<BlockItem> PALM_FENCE_ITEM = register("palm_fence", PALM_FENCE);
    public static final DeferredItem<BlockItem> PALM_FENCE_GATE_ITEM = register("palm_fence_gate", PALM_FENCE_GATE);
    public static final DeferredItem<BlockItem> PALM_PRESSURE_PLATE_ITEM = register("palm_pressure_plate", PALM_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> PALM_SLAB_ITEM = register("palm_slab", PALM_SLAB);
    public static final DeferredItem<BlockItem> PALM_STAIRS_ITEM = register("palm_stairs", PALM_STAIRS);
    public static final DeferredItem<BlockItem> PALM_DOOR_ITEM = register("palm_door", PALM_DOOR);
    public static final DeferredItem<BlockItem> PALM_TRAPDOOR_ITEM = register("palm_trapdoor", PALM_TRAPDOOR);
    public static final DeferredItem<SignItem> PALM_SIGN_ITEM =
            registerSign("palm_sign", PALM_SIGN, PALM_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> PALM_HANGING_SIGN_ITEM =
            registerHangingSign("palm_hanging_sign", PALM_HANGING_SIGN, PALM_WALL_HANGING_SIGN);

    @I18n(en_us = "Palm Crown", zh_cn = "棕榈树冠", zh_tw = "棕櫚樹冠")
    @BasicBlock
    @VanillaTag(names = "mineable/axe", type = TagType.Block)
    public static final DeferredBlock<Block> PALM_CROWN = register("palm_crown", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));
    public static final DeferredItem<BlockItem> PALM_CROWN_ITEM = register("palm_crown", p -> new BlockItem(PALM_CROWN.get(), p) {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 300;
        }
    });

    @AllWood
    @I18n(en_us = "Baobab Planks", zh_cn = "猴面包木板", zh_tw = "猴麵包木材")
    public static final DeferredBlock<Block> BAOBAB_PLANKS = register("baobab_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    @I18n(en_us = "Baobab Log", zh_cn = "猴面包原木", zh_tw = "猴麵包原木")
    @CTag(names = "baobab_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> BAOBAB_LOG = register("baobab_log", p -> new StrippableLog(p, ModBlocks.STRIPPED_BAOBAB_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LOG));
    @I18n(en_us = "Stripped Baobab Log", zh_cn = "去皮猴面包原木", zh_tw = "剝皮猴麵包原木")
    @CTag(names = "baobab_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_LOG = register("stripped_baobab_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG));
    @I18n(en_us = "Baobab Wood", zh_cn = "猴面包木", zh_tw = "猴麵包木塊")
    @CTag(names = "baobab_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> BAOBAB_WOOD = register("baobab_wood", p -> new StrippableLog(p, ModBlocks.STRIPPED_BAOBAB_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD));
    @I18n(en_us = "Stripped Baobab Wood", zh_cn = "去皮猴面包木", zh_tw = "剝皮猴麵包木塊")
    @CTag(names = "baobab_logs", type = TagType.Block)
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_WOOD = register("stripped_baobab_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_WOOD));
    @I18n(en_us = "Baobab Button", zh_cn = "猴面包木按钮", zh_tw = "猴麵包木按鈕")
    public static final DeferredBlock<ButtonBlock> BAOBAB_BUTTON = register("baobab_button", props -> new ButtonBlock(ModWoodSettings.BAOBAB.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_BUTTON));
    @I18n(en_us = "Baobab Fence", zh_cn = "猴面包木栅栏", zh_tw = "猴麵包木柵欄")
    public static final DeferredBlock<FenceBlock> BAOBAB_FENCE = register("baobab_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE));
    @I18n(en_us = "Baobab Fence Gate", zh_cn = "猴面包木栅栏门", zh_tw = "猴麵包木柵欄門")
    public static final DeferredBlock<FenceGateBlock> BAOBAB_FENCE_GATE = register("baobab_fence_gate", props -> new FenceGateBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE));
    @I18n(en_us = "Baobab Pressure Plate", zh_cn = "猴面包木压力板", zh_tw = "猴麵包木压力板")
    public static final DeferredBlock<PressurePlateBlock> BAOBAB_PRESSURE_PLATE = register("baobab_pressure_plate", props -> new PressurePlateBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE));
    @I18n(en_us = "Baobab Slab", zh_cn = "猴面包木台阶", zh_tw = "猴麵包木半磚")
    public static final DeferredBlock<SlabBlock> BAOBAB_SLAB = register("baobab_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));
    @I18n(en_us = "Baobab Stairs", zh_cn = "猴面包木楼梯", zh_tw = "猴麵包木樓梯")
    public static final DeferredBlock<StairBlock> BAOBAB_STAIRS = register("baobab_stairs",
            props -> new StairBlock(BAOBAB_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS));
    @I18n(en_us = "Baobab Door", zh_cn = "猴面包木门", zh_tw = "猴麵包木門")
    public static final DeferredBlock<DoorBlock> BAOBAB_DOOR = register("baobab_door",
            props -> new DoorBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR));
    @I18n(en_us = "Baobab Trapdoor", zh_cn = "猴面包木活板门", zh_tw = "猴麵包木地板門")
    public static final DeferredBlock<TrapDoorBlock> BAOBAB_TRAPDOOR = register("baobab_trapdoor",
            props -> new TrapDoorBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR));
    @I18n(en_us = "Baobab Sign", zh_cn = "猴面包木告示牌", zh_tw = "猴麵包木告示牌")
    public static final DeferredBlock<StandingSignBlock> BAOBAB_SIGN = register("baobab_sign",
            props -> new StandingSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SIGN));
    @I18n(en_us = "Baobab Wall Sign", zh_cn = "墙上的猴面包木告示牌", zh_tw = "牆上的猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_sign")
    public static final DeferredBlock<WallSignBlock> BAOBAB_WALL_SIGN = register("baobab_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_SIGN).lootFrom(BAOBAB_SIGN));
    @I18n(en_us = "Baobab Hanging Sign", zh_cn = "悬挂式猴面包木告示牌", zh_tw = "懸挂式猴麵包木告示牌")
    public static final DeferredBlock<CeilingHangingSignBlock> BAOBAB_HANGING_SIGN = register("baobab_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_HANGING_SIGN));
    @I18n(en_us = "Baobab Wall Hanging Sign", zh_cn = "墙上的悬挂式猴面包木告示牌", zh_tw = "牆上的懸挂式猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_hanging_sign")
    public static final DeferredBlock<WallHangingSignBlock> BAOBAB_WALL_HANGING_SIGN = register("baobab_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.BAOBAB.woodType, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_HANGING_SIGN).lootFrom(BAOBAB_HANGING_SIGN));
    public static final DeferredItem<BlockItem> BAOBAB_LOG_ITEM = register("baobab_log", BAOBAB_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_BAOBAB_LOG_ITEM = register("stripped_baobab_log", STRIPPED_BAOBAB_LOG);
    public static final DeferredItem<BlockItem> BAOBAB_WOOD_ITEM = register("baobab_wood", BAOBAB_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_BAOBAB_WOOD_ITEM = register("stripped_baobab_wood", STRIPPED_BAOBAB_WOOD);
    public static final DeferredItem<BlockItem> BAOBAB_PLANKS_ITEM = register("baobab_planks", BAOBAB_PLANKS);
    public static final DeferredItem<BlockItem> BAOBAB_BUTTON_ITEM = register("baobab_button", BAOBAB_BUTTON);
    public static final DeferredItem<BlockItem> BAOBAB_FENCE_ITEM = register("baobab_fence", BAOBAB_FENCE);
    public static final DeferredItem<BlockItem> BAOBAB_FENCE_GATE_ITEM = register("baobab_fence_gate", BAOBAB_FENCE_GATE);
    public static final DeferredItem<BlockItem> BAOBAB_PRESSURE_PLATE_ITEM = register("baobab_pressure_plate", BAOBAB_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> BAOBAB_SLAB_ITEM = register("baobab_slab", BAOBAB_SLAB);
    public static final DeferredItem<BlockItem> BAOBAB_STAIRS_ITEM = register("baobab_stairs", BAOBAB_STAIRS);
    public static final DeferredItem<BlockItem> BAOBAB_DOOR_ITEM = register("baobab_door", BAOBAB_DOOR);
    public static final DeferredItem<BlockItem> BAOBAB_TRAPDOOR_ITEM = register("baobab_trapdoor", BAOBAB_TRAPDOOR);
    public static final DeferredItem<SignItem> BAOBAB_SIGN_ITEM = registerSign("baobab_sign", BAOBAB_SIGN, BAOBAB_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> BAOBAB_HANGING_SIGN_ITEM = registerHangingSign("baobab_hanging_sign", BAOBAB_HANGING_SIGN, BAOBAB_WALL_HANGING_SIGN);

    @I18n(en_us = "Palm Leaves", zh_cn = "棕榈树叶", zh_tw = "棕櫚樹葉")
    @BasicBlock
    @VanillaTag(names = "leaves", type = TagType.Block)
    public static final DeferredBlock<LeavesBlock> PALM_LEAVES = register("palm_leaves", LeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    public static final DeferredItem<BlockItem> PALM_LEAVES_ITEM = register("palm_leaves", PALM_LEAVES);
    @I18n(en_us = "Baobab Leaves", zh_cn = "猴面包树叶", zh_tw = "猴麵包樹葉")
    @CubeAll
    @VanillaTag(names = "leaves", type = TagType.Block)
    public static final DeferredBlock<LeavesBlock> BAOBAB_LEAVES = register("baobab_leaves", LeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES));
    public static final DeferredItem<BlockItem> BAOBAB_LEAVES_ITEM = register("baobab_leaves", BAOBAB_LEAVES);

    //TODO: TreeGrower
    @I18n(en_us = "Palm Sapling", zh_cn = "棕榈树苗", zh_tw = "棕櫚樹苗")
    @Cross(item = false)
    @VanillaTag(names = "saplings", type = TagType.Block)
    public static final DeferredBlock<SaplingBlock> PALM_SAPLING = register("palm_sapling", props -> new SaplingBlock(ModTreeGrowers.PALM, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));
    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> PALM_SAPLING_ITEM = register("palm_sapling", PALM_SAPLING);
    @I18n(en_us = "Baobab Sapling", zh_cn = "猴面包树苗", zh_tw = "猴麵包樹苗")
    @Cross(item = false)
    @VanillaTag(names = "saplings", type = TagType.Block)
    public static final DeferredBlock<SaplingBlock> BAOBAB_SAPLING = register("baobab_sapling", props -> new SaplingBlock(ModTreeGrowers.BAOBAB, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING));
    @BasicBlockLocatedItem
    public static final DeferredItem<BlockItem> BAOBAB_SAPLING_ITEM = register("baobab_sapling", BAOBAB_SAPLING);

    @AllBrick
    @I18n(en_us = "Andesite Bricks", zh_cn = "安山岩砖", zh_tw = "安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> ANDESITE_BRICKS = register("andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Cracked Andesite Bricks", zh_cn = "裂纹安山岩砖", zh_tw = "裂紋安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CRACKED_ANDESITE_BRICKS = register("cracked_andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Stairs", zh_cn = "安山岩砖楼梯", zh_tw = "安山岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    public static final DeferredBlock<StairBlock> ANDESITE_BRICK_STAIRS = register("andesite_brick_stairs", props -> new StairBlock(ANDESITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Slab", zh_cn = "安山岩砖台阶", zh_tw = "安山岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    public static final DeferredBlock<SlabBlock> ANDESITE_BRICK_SLAB = register("andesite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Wall", zh_cn = "安山岩砖墙", zh_tw = "安山岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> ANDESITE_BRICK_WALL = register("andesite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.STONE));
    public static final DeferredItem<BlockItem> ANDESITE_BRICKS_ITEM = register("andesite_bricks", ANDESITE_BRICKS);
    public static final DeferredItem<BlockItem> CRACKED_ANDESITE_BRICKS_ITEM = register("cracked_andesite_bricks", CRACKED_ANDESITE_BRICKS);
    public static final DeferredItem<BlockItem> ANDESITE_BRICK_STAIRS_ITEM = register("andesite_brick_stairs", ANDESITE_BRICK_STAIRS);
    public static final DeferredItem<BlockItem> ANDESITE_BRICK_SLAB_ITEM = register("andesite_brick_slab", ANDESITE_BRICK_SLAB);
    public static final DeferredItem<BlockItem> ANDESITE_BRICK_WALL_ITEM = register("andesite_brick_wall", ANDESITE_BRICK_WALL);

    @AllBrick
    @I18n(en_us = "Diorite Bricks", zh_cn = "闪长岩砖", zh_tw = "閃長岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> DIORITE_BRICKS = register("diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Cracked Diorite Bricks", zh_cn = "裂纹闪长岩砖", zh_tw = "裂紋閃長岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CRACKED_DIORITE_BRICKS = register("cracked_diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Stairs", zh_cn = "闪长岩砖楼梯", zh_tw = "閃長岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    public static final DeferredBlock<StairBlock> DIORITE_BRICK_STAIRS = register("diorite_brick_stairs", props -> new StairBlock(DIORITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Slab", zh_cn = "闪长岩砖台阶", zh_tw = "閃長岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    public static final DeferredBlock<SlabBlock> DIORITE_BRICK_SLAB = register("diorite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Wall", zh_cn = "闪长岩砖墙", zh_tw = "閃長岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> DIORITE_BRICK_WALL = register("diorite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.QUARTZ));
    public static final DeferredItem<BlockItem> DIORITE_BRICKS_ITEM = register("diorite_bricks", DIORITE_BRICKS);
    public static final DeferredItem<BlockItem> CRACKED_DIORITE_BRICKS_ITEM = register("cracked_diorite_bricks", CRACKED_DIORITE_BRICKS);
    public static final DeferredItem<BlockItem> DIORITE_BRICK_STAIRS_ITEM = register("diorite_brick_stairs", DIORITE_BRICK_STAIRS);
    public static final DeferredItem<BlockItem> DIORITE_BRICK_SLAB_ITEM = register("diorite_brick_slab", DIORITE_BRICK_SLAB);
    public static final DeferredItem<BlockItem> DIORITE_BRICK_WALL_ITEM = register("diorite_brick_wall", DIORITE_BRICK_WALL);

    @AllBrick
    @I18n(en_us = "Granite Bricks", zh_cn = "花岗岩砖", zh_tw = "花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> GRANITE_BRICKS = register("granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Cracked Granite Bricks", zh_cn = "裂纹花岗岩砖", zh_tw = "裂紋花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CRACKED_GRANITE_BRICKS = register("cracked_granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Stairs", zh_cn = "花岗岩砖楼梯", zh_tw = "花崗岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    public static final DeferredBlock<StairBlock> GRANITE_BRICK_STAIRS = register("granite_brick_stairs", props -> new StairBlock(GRANITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Slab", zh_cn = "花岗岩砖台阶", zh_tw = "花崗岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    public static final DeferredBlock<SlabBlock> GRANITE_BRICK_SLAB = register("granite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Wall", zh_cn = "花岗岩砖墙", zh_tw = "花崗岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> GRANITE_BRICK_WALL = register("granite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DIRT));
    public static final DeferredItem<BlockItem> GRANITE_BRICKS_ITEM = register("granite_bricks", GRANITE_BRICKS);
    public static final DeferredItem<BlockItem> CRACKED_GRANITE_BRICKS_ITEM = register("cracked_granite_bricks", CRACKED_GRANITE_BRICKS);
    public static final DeferredItem<BlockItem> GRANITE_BRICK_STAIRS_ITEM = register("granite_brick_stairs", GRANITE_BRICK_STAIRS);
    public static final DeferredItem<BlockItem> GRANITE_BRICK_SLAB_ITEM = register("granite_brick_slab", GRANITE_BRICK_SLAB);
    public static final DeferredItem<BlockItem> GRANITE_BRICK_WALL_ITEM = register("granite_brick_wall", GRANITE_BRICK_WALL);

    @AllBrick
    @I18n(en_us = "Blue Ice Bricks", zh_cn = "蓝冰砖", zh_tw = "藍冰磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> BLUE_ICE_BRICKS = register("blue_ice_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Cracked Blue Ice Bricks", zh_cn = "裂纹蓝冰砖", zh_tw = "裂紋藍冰磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CRACKED_BLUE_ICE_BRICKS = register("cracked_blue_ice_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Stairs", zh_cn = "蓝冰砖楼梯", zh_tw = "藍冰磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    public static final DeferredBlock<StairBlock> BLUE_ICE_BRICK_STAIRS = register("blue_ice_brick_stairs", props -> new StairBlock(BLUE_ICE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Slab", zh_cn = "蓝冰砖台阶", zh_tw = "藍冰磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    public static final DeferredBlock<SlabBlock> BLUE_ICE_BRICK_SLAB = register("blue_ice_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Wall", zh_cn = "蓝冰砖墙", zh_tw = "藍冰磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> BLUE_ICE_BRICK_WALL = register("blue_ice_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.ICE));
    public static final DeferredItem<BlockItem> BLUE_ICE_BRICKS_ITEM = register("blue_ice_bricks", BLUE_ICE_BRICKS);
    public static final DeferredItem<BlockItem> CRACKED_BLUE_ICE_BRICKS_ITEM = register("cracked_blue_ice_bricks", CRACKED_BLUE_ICE_BRICKS);
    public static final DeferredItem<BlockItem> BLUE_ICE_BRICK_STAIRS_ITEM = register("blue_ice_brick_stairs", BLUE_ICE_BRICK_STAIRS);
    public static final DeferredItem<BlockItem> BLUE_ICE_BRICK_SLAB_ITEM = register("blue_ice_brick_slab", BLUE_ICE_BRICK_SLAB);
    public static final DeferredItem<BlockItem> BLUE_ICE_BRICK_WALL_ITEM = register("blue_ice_brick_wall", BLUE_ICE_BRICK_WALL);

    public static final DeferredBlock<LiquidBlock> MILK_BLOCK = REGISTER.register("milk", () -> new LiquidBlock(ModFluids.MILK.get(), BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().replaceable().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>
    entity(String name,
             Type<?> type,
             BlockEntityType.BlockEntitySupplier<T> factory,
             DeferredBlock<?>... blocks) {

        return TILES.register(name, () -> BlockEntityType.Builder.of(factory, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)).build(type));
    }
}
