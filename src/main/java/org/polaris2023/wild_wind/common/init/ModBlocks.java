package org.polaris2023.wild_wind.common.init;

import java.util.Arrays;
import java.util.function.BiConsumer;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.enums.TagType;
import org.polaris2023.annotation.handler.RegistryBlockItemHandler;
import org.polaris2023.annotation.handler.RegistryHandler;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.block.AllBrick;
import org.polaris2023.annotation.modelgen.block.AllWood;
import org.polaris2023.annotation.modelgen.block.BasicBlock;
import org.polaris2023.annotation.modelgen.block.Carpet;
import org.polaris2023.annotation.modelgen.block.Cross;
import org.polaris2023.annotation.modelgen.block.CubeAll;
import org.polaris2023.annotation.modelgen.block.CubeAllFor;
import org.polaris2023.annotation.modelgen.block.CubeBottomTop;
import org.polaris2023.annotation.modelgen.block.CubeColumn;
import org.polaris2023.annotation.modelgen.block.CubeOrientable;
import org.polaris2023.annotation.modelgen.block.Fence;
import org.polaris2023.annotation.modelgen.block.Slab;
import org.polaris2023.annotation.modelgen.block.Stairs;
import org.polaris2023.annotation.modelgen.block.Wall;
import org.polaris2023.annotation.modelgen.item.BasicBlockItem;
import org.polaris2023.annotation.modelgen.item.BasicBlockLocatedItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.annotation.modelgen.item.ParentItem;
import org.polaris2023.annotation.register.RegistryBlockItem;
import org.polaris2023.annotation.tag.CTag;
import org.polaris2023.annotation.tag.VanillaTag;
import org.polaris2023.annotation.tag.WildWindTag;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.block.AquaticBlock;
import org.polaris2023.wild_wind.common.block.AquaticDoublePlantBlock;
import org.polaris2023.wild_wind.common.block.AshLayerBlock;
import org.polaris2023.wild_wind.common.block.BrazierBlock;
import org.polaris2023.wild_wind.common.block.BrittleIceBlock;
import org.polaris2023.wild_wind.common.block.CattailsBlock;
import org.polaris2023.wild_wind.common.block.CookingPotBlock;
import org.polaris2023.wild_wind.common.block.DuckweedBlock;
import org.polaris2023.wild_wind.common.block.FluffyDandelionBlock;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.block.LargeThornBlock;
import org.polaris2023.wild_wind.common.block.LotusBlock;
import org.polaris2023.wild_wind.common.block.NeoBedBlock;
import org.polaris2023.wild_wind.common.block.PointedIcicleBlock;
import org.polaris2023.wild_wind.common.block.PresentBlock;
import org.polaris2023.wild_wind.common.block.PyroclastBlock;
import org.polaris2023.wild_wind.common.block.QuicksandBlock;
import org.polaris2023.wild_wind.common.block.ReedsBlock;
import org.polaris2023.wild_wind.common.block.RemainsBlock;
import org.polaris2023.wild_wind.common.block.ScorchPyroclastBlock;
import org.polaris2023.wild_wind.common.block.SculkCiliaBlock;
import org.polaris2023.wild_wind.common.block.SculkJawBlock;
import org.polaris2023.wild_wind.common.block.SculkTendrilBlock;
import org.polaris2023.wild_wind.common.block.SculkVinesBlock;
import org.polaris2023.wild_wind.common.block.SculkVinesPlantBlock;
import org.polaris2023.wild_wind.common.block.SiltBlock;
import org.polaris2023.wild_wind.common.block.SimpleDoublePlantBlock;
import org.polaris2023.wild_wind.common.block.SimpleGrassBlock;
import org.polaris2023.wild_wind.common.block.StrippableLog;
import org.polaris2023.wild_wind.common.block.ThornBlock;
import org.polaris2023.wild_wind.common.block.TinyCactusBlock;
import org.polaris2023.wild_wind.common.block.TrappedPresentBlock;
import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.DuckweedBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.ModBannerBlockEntity;
import org.polaris2023.wild_wind.common.block.modified.ModBannerBlock;
import org.polaris2023.wild_wind.common.block.modified.ModWallBannerBlock;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;
import static org.polaris2023.wild_wind.common.init.ModInitializer.TILES;
import static org.polaris2023.wild_wind.util.interfaces.registry.BlockRegistry.register;
import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.registerHangingSign;
import static org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry.registerSign;

@RegistryHandler(RegType.Block)
@SuppressWarnings("unused")
public class ModBlocks {
    public static final DeferredRegister.Blocks REGISTER =
            DeferredRegister.createBlocks(MOD_ID);

    public static final BlockBehaviour.Properties EMPTY = BlockBehaviour.Properties.of();
    @I18n(en_us = "Glow Mucus", zh_cn = "萤光黏液", zh_tw = "螢光黏液")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<GlowMucusBlock> GLOW_MUCUS = register("glow_mucus", GlowMucusBlock::new, BlockBehaviour.Properties.of());

    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    @RegistryBlockItem
    @BasicItem
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar", BlockBehaviour.Properties.of().lightLevel(s -> 6).noLootTable());

    @CTag(names = "milk", type = TagType.Block)
    public static final DeferredBlock<LiquidBlock> MILK = REGISTER.register("milk",
            () -> new LiquidBlock((FlowingFluid) NeoForgeMod.MILK.get(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).noCollission().replaceable().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));

    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    @RegistryBlockItem
    @BasicItem
    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");

    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    @RegistryBlockItem
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");

    @I18n(en_us = "Silk Cocoon", zh_cn = "蛛丝巢穴", zh_tw = "蛛絲巢穴")
    @RegistryBlockItem
    public static final DeferredBlock<Block> SILK_COCOON = register("silk_cocoon", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cobweb Cover", zh_cn = "蛛丝覆层", zh_tw = "蛛絲覆層")
    @RegistryBlockItem
    public static final DeferredBlock<Block> COBWEB_COVER = register("cobweb_cover", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cobweb Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> COBWEB_MUCOSA = register("cobweb_mucosa", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    @RegistryBlockItem
    @BasicItem
    public static final DeferredBlock<ReedsBlock> REEDS = register("reeds", ReedsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    @BasicItem
    @RegistryBlockItem
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
    @BasicBlockLocatedItem
    @RegistryBlockItem
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
    @RegistryBlockItem
    @BasicItem
    public static final DeferredBlock<DuckweedBlock> DUCKWEED = register("duckweed", DuckweedBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DuckweedBlockEntity>> DUCKWEED_TILE =
            entity("duckweed", DSL.remainderType(), DuckweedBlockEntity::new, DUCKWEED);

    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    @CubeAllFor(
            cube = @CubeAll(render_type = "translucent", all = "wild_wind:block/brittle_ice_0"),
            min = 0,
            max = 3,
            def = "wild_wind:block/brittle_ice"
    )
    @RegistryBlockItem
    @VanillaTag(names = {"snow_layer_cannot_survive_on", "mineable/pickaxe", "ice"}, type = TagType.Block)
    @CTag(names = "ice_skip", type = TagType.Block)
    public static final DeferredBlock<BrittleIceBlock> BRITTLE_ICE = register("brittle_ice", BrittleIceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
            .strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));

    @I18n(en_us = "Pyroclast", zh_cn = "熔屑岩", zh_tw = "熔屑岩")
    @CubeAllFor(
            cube = @CubeAll(all = "wild_wind:block/pyroclast_0"),
            min = 0,
            max = 4,
            def = "wild_wind:block/pyroclast"
    )
    @RegistryBlockItem
    @VanillaTag(names = {"snow_layer_cannot_survive_on", "mineable/pickaxe"}, type = TagType.Block)
    public static final DeferredBlock<PyroclastBlock> PYROCLAST = register("pyroclast", PyroclastBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));

    @I18n(en_us = "Pyroclast", zh_cn = "灼燃熔屑岩", zh_tw = "灼燃熔屑岩")
    //TODO Model fix
    @CubeColumn(side = "wild_wind:block/block_placeholder", end = "wild_wind:block/block_placeholder")
    //方块物品手动注册
    @VanillaTag(names = {"snow_layer_cannot_survive_on", "mineable/pickaxe"}, type = TagType.Block)
    public static final DeferredBlock<PyroclastBlock> SCORCH_PYROCLAST = REGISTER.register("scorch_pyroclast", () -> new ScorchPyroclastBlock(BlockBehaviour.Properties.ofFullCopy(PYROCLAST.get()).lightLevel(s -> 9 - s.getValue(PyroclastBlock.AGE))));

    @I18n(en_us = "Ash Block", zh_cn = "灰烬块", zh_tw = "灰烬块")
    @BasicBlock
    @RegistryBlockItem
    @VanillaTag(names = "mineable/shovel", type = TagType.Block)
    public static final DeferredBlock<Block> ASH_BLOCK = register("ash_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));


    @I18n(en_us = "Ash", zh_cn = "灰烬", zh_tw = "灰烬")
    @VanillaTag(names = {"replaceable", "mineable/shovel"}, type = TagType.Block)
    @ParentItem(parent = "wild_wind:block/ash_1")
    @RegistryBlockItem
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
    public static final DeferredBlock<CarpetBlock> CARPET = register("carpet", CarpetBlock::new, BlockBehaviour.Properties.of().strength(0.1F).sound(SoundType.WOOL).ignitedByLava());


    @I18n(en_us = "Concrete", zh_cn = "混凝土", zh_tw = "混凝土")
    @BasicBlock
    @RegistryBlockItem
    @CTag(names = "concretes", type = TagType.Block)
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    public static final DeferredBlock<Block> CONCRETE = register("concrete", BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE).strength(0.8F).ignitedByLava());

    @I18n(en_us = "Concrete Powder", zh_cn = "混凝土粉末", zh_tw = "混凝土粉末")
    @BasicBlock
    @CTag(names = "concrete_powders", type = TagType.Block)
    @VanillaTag(names = {"mineable/shovel", "camel_sand_step_sound_blocks"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CONCRETE_POWDER = register("concrete_powder", properties -> new ConcretePowderBlock(CONCRETE.get(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER).ignitedByLava());
    @I18n(en_us = "Glazed Terracotta", zh_cn = "带釉陶瓦", zh_tw = "带釉陶瓦")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CTag(names = "glazed_terracottas", type = TagType.Block)
    @BasicBlockItem
    @RegistryBlockItem
    public static final DeferredBlock<GlazedTerracottaBlock> GLAZED_TERRACOTTA = register("glazed_terracotta", GlazedTerracottaBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_GLAZED_TERRACOTTA));

    @I18n(en_us = "Salt Block", zh_cn = "盐块", zh_tw = "鹽塊")
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> SALT_BLOCK = register("salt_block", Block::new, BlockBehaviour.Properties.of()
            .strength(3F)
            .requiresCorrectToolForDrops()
            .isRedstoneConductor((_0, _1, _2) -> true));

    @I18n(en_us = "Salt Ore", zh_cn = "盐矿石", zh_tw = "鹽礦石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> SALT_ORE = register("salt_ore", p -> new DropExperienceBlock(UniformInt.of(2, 5), p), BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(3));

    @I18n(en_us = "Deepslate Salt Ore", zh_cn = "深层盐矿石", zh_tw = "深層鹽礦石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SALT_ORE = register("deepslate_salt_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties), BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5F, 3)
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE));

    @CubeBottomTop(side = "wild_wind:block/glistering_melon_side",
            bottom = "wild_wind:block/glistering_melon_side",
            top = "wild_wind:block/glistering_melon_top")
    @I18n(en_us = "Glistering Melon", zh_cn = "闪烁的西瓜", zh_tw = "閃爍的西瓜")
    @VanillaTag(names = {"sword_efficient", "enderman_holdable", "mineable/axe"}, type = TagType.Block)
    @BasicBlockItem
    @RegistryBlockItem
    public static final DeferredBlock<Block> GLISTERING_MELON = register("glistering_melon", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.GOLD));

    @I18n(en_us = "Stone Wall", zh_tw = "石牆", zh_cn = "石墙")
    @Wall(wall = "minecraft:block/stone")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> STONE_WALL = register("stone_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    @I18n(en_us = "Polished Stone", zh_cn = "磨制石头", zh_tw = "磨製石頭")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_STONE = register("polished_stone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.5f));
    @I18n(en_us = "Polished Stone Wall", zh_cn = "磨制石墙", zh_tw = "磨製石牆")
    @Wall(wall = "wild_wind:block/polished_stone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> POLISHED_STONE_WALL = register("polished_stone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.POLISHED_STONE.get())), EMPTY);
    @I18n(en_us = "Polished Stone Stairs", zh_cn = "磨制石楼梯", zh_tw = "磨製石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_stone", top = "wild_wind:block/polished_stone", side = "wild_wind:block/polished_stone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_STONE_STAIRS = register("polished_stone_stairs", properties -> new StairBlock(POLISHED_STONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    @I18n(en_us = "Polished Stone Slab", zh_cn = "磨制石台阶", zh_tw = "磨製石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_stone", side = "wild_wind:block/polished_stone", top = "wild_wind:block/polished_stone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_STONE_SLAB = register("polished_stone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));

    @I18n(en_us = "Polished Granite Wall", zh_cn = "磨制花岗岩墙", zh_tw = "磨製花崗岩牆")
    @Wall(wall = "minecraft:block/polished_granite")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_GRANITE_WALL = register("polished_granite_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE)), EMPTY);
    @I18n(en_us = "Polished Diorite Wall", zh_cn = "磨制闪长岩墙", zh_tw = "磨製閃長岩牆")
    @Wall(wall = "minecraft:block/polished_diorite")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_DIORITE_WALL = register("polished_diorite_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE)), EMPTY);
    @I18n(en_us = "Polished Andesite Wall", zh_cn = "磨制安山岩墙", zh_tw = "磨製安山岩牆")
    @Wall(wall = "minecraft:block/polished_andesite")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_ANDESITE_WALL = register("polished_andesite_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)), EMPTY);

    @AllWood
    @I18n(en_us = "Azalea Planks", zh_cn = "杜鹃木板", zh_tw = "杜鵑木材")
    @VanillaTag(names = "planks", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> AZALEA_PLANKS = register("azalea_planks",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    @I18n(en_us = "Azalea Log", zh_cn = "杜鹃原木", zh_tw = "杜鵑原木")
    @CTag(names = "azalea_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StrippableLog> AZALEA_LOG = register("azalea_log",
            p -> new StrippableLog(p, ModBlocks.STRIPPED_AZALEA_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LOG));
    @I18n(en_us = "Stripped Azalea Log", zh_cn = "去皮杜鹃原木", zh_tw = "剝皮杜鵑原木")
    @CTag(names = "azalea_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_LOG = register("stripped_azalea_log",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG));
    @I18n(en_us = "Azalea Wood", zh_cn = "杜鹃木", zh_tw = "杜鵑木塊")
    @CTag(names = "azalea_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> AZALEA_WOOD = register("azalea_wood",
            p -> new StrippableLog(p, ModBlocks.STRIPPED_AZALEA_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD));
    @I18n(en_us = "Stripped Azalea Wood", zh_cn = "去皮杜鹃木", zh_tw = "剝皮杜鵑木塊")
    @CTag(names = "azalea_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_WOOD = register("stripped_azalea_wood",
            RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD));
    @I18n(en_us = "Azalea Button", zh_cn = "杜鹃木按钮", zh_tw = "杜鵑木按鈕")
    @VanillaTag(names = "wooden_buttons", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<ButtonBlock> AZALEA_BUTTON = register("azalea_button",
            props -> new ButtonBlock(ModWoodSettings.AZALEA.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_BUTTON));
    @I18n(en_us = "Azalea Fence", zh_cn = "杜鹃木栅栏", zh_tw = "杜鵑木柵欄")
    @VanillaTag(names = "wooden_fences", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceBlock> AZALEA_FENCE = register("azalea_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE));
    @I18n(en_us = "Azalea Fence Gate", zh_cn = "杜鹃木栅栏门", zh_tw = "杜鵑木柵欄門")
    @VanillaTag(names = "fence_gates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceGateBlock> AZALEA_FENCE_GATE = register("azalea_fence_gate",
            props -> new FenceGateBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE));
    @I18n(en_us = "Azalea Pressure Plate", zh_cn = "杜鹃木压力板", zh_tw = "杜鵑木压力板")
    @VanillaTag(names = "wooden_pressure_plates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<PressurePlateBlock> AZALEA_PRESSURE_PLATE = register("azalea_pressure_plate",
            props -> new PressurePlateBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE));
    @I18n(en_us = "Azalea Slab", zh_cn = "杜鹃木台阶", zh_tw = "杜鵑木半磚")
    @VanillaTag(names = "wooden_slabs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> AZALEA_SLAB = register("azalea_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
    @I18n(en_us = "Azalea Stairs", zh_cn = "杜鹃木楼梯", zh_tw = "杜鵑木樓梯")
    @VanillaTag(names = "wooden_stairs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> AZALEA_STAIRS = register("azalea_stairs",
            props -> new StairBlock(AZALEA_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS));
    @I18n(en_us = "Azalea Door", zh_cn = "杜鹃木门", zh_tw = "杜鵑木門")
    @VanillaTag(names = "wooden_doors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<DoorBlock> AZALEA_DOOR = register("azalea_door",
            props -> new DoorBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
    @I18n(en_us = "Azalea Trapdoor", zh_cn = "杜鹃木活板门", zh_tw = "杜鵑木地板門")
    @VanillaTag(names = "wooden_trapdoors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<TrapDoorBlock> AZALEA_TRAPDOOR = register("azalea_trapdoor",
            props -> new TrapDoorBlock(ModWoodSettings.AZALEA.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR));
    @I18n(en_us = "Azalea Sign", zh_cn = "杜鹃木告示牌", zh_tw = "杜鵑木告示牌")
    @VanillaTag(names = "standing_signs", type = TagType.Block)
    public static final DeferredBlock<StandingSignBlock> AZALEA_SIGN = register("azalea_sign",
            props -> new StandingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SIGN));
    @I18n(en_us = "Azalea Wall Sign", zh_cn = "墙上的杜鹃木告示牌", zh_tw = "牆上的杜鵑木告示牌", descriptionId = "block.wild_wind.azalea_wall_sign")
    @VanillaTag(names = "wall_signs", type = TagType.Block)
    public static final DeferredBlock<WallSignBlock> AZALEA_WALL_SIGN = register("azalea_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_SIGN).lootFrom(AZALEA_SIGN));
    @I18n(en_us = "Azalea Hanging Sign", zh_cn = "悬挂式杜鹃木告示牌", zh_tw = "懸挂式杜鵑木告示牌")
    @VanillaTag(names = "ceiling_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<CeilingHangingSignBlock> AZALEA_HANGING_SIGN = register("azalea_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_HANGING_SIGN));
    @I18n(en_us = "Azalea Wall Hanging Sign", zh_cn = "墙上的杜鹃木悬挂式告示牌", zh_tw = "牆上的杜鵑木懸挂式告示牌", descriptionId = "block.wild_wind.azalea_wall_hanging_sign")
    @VanillaTag(names = "wall_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<WallHangingSignBlock> AZALEA_WALL_HANGING_SIGN = register("azalea_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.AZALEA.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_HANGING_SIGN).lootFrom(AZALEA_HANGING_SIGN));
    public static final DeferredItem<SignItem> AZALEA_SIGN_ITEM =
            registerSign("azalea_sign", AZALEA_SIGN, AZALEA_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> AZALEA_HANGING_SIGN_ITEM =
            registerHangingSign("azalea_hanging_sign", AZALEA_HANGING_SIGN, AZALEA_WALL_HANGING_SIGN);

    @AllWood
    @I18n(en_us = "Palm Planks", zh_cn = "棕榈木板", zh_tw = "棕櫚木材")
    @VanillaTag(names = "planks", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> PALM_PLANKS = register("palm_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    @I18n(en_us = "Palm Log", zh_cn = "棕榈原木", zh_tw = "棕櫚原木")
    @CTag(names = "palm_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> PALM_LOG = register("palm_log", p -> new StrippableLog(p, ModBlocks.STRIPPED_PALM_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
    @I18n(en_us = "Stripped Palm Log", zh_cn = "去皮棕榈原木", zh_tw = "剝皮棕櫚原木")
    @CTag(names = "palm_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_LOG = register("stripped_palm_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG));
    @I18n(en_us = "Palm Wood", zh_cn = "棕榈木", zh_tw = "棕櫚木塊")
    @CTag(names = "palm_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> PALM_WOOD = register("palm_wood", p -> new StrippableLog(p, ModBlocks.STRIPPED_PALM_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD));
    @I18n(en_us = "Stripped Palm Wood", zh_cn = "去皮棕榈木", zh_tw = "剝皮棕櫚木塊")
    @CTag(names = "palm_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_PALM_WOOD = register("stripped_palm_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));
    @I18n(en_us = "Palm Button", zh_cn = "棕榈木按钮", zh_tw = "棕櫚木按鈕")
    @VanillaTag(names = "wooden_buttons", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<ButtonBlock> PALM_BUTTON = register("palm_button", props -> new ButtonBlock(ModWoodSettings.PALM.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_BUTTON));
    @I18n(en_us = "Palm Fence", zh_cn = "棕榈木栅栏", zh_tw = "棕櫚木柵欄")
    @VanillaTag(names = "wooden_fences", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceBlock> PALM_FENCE = register("palm_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE));
    @I18n(en_us = "Palm Fence Gate", zh_cn = "棕榈木栅栏门", zh_tw = "棕櫚木柵欄門")
    @VanillaTag(names = "fence_gates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceGateBlock> PALM_FENCE_GATE = register("palm_fence_gate", props -> new FenceGateBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    @I18n(en_us = "Palm Pressure Plate", zh_cn = "棕榈木压力板", zh_tw = "棕櫚木压力板")
    @VanillaTag(names = "wooden_pressure_plates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<PressurePlateBlock> PALM_PRESSURE_PLATE = register("palm_pressure_plate", props -> new PressurePlateBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE));
    @I18n(en_us = "Palm Slab", zh_cn = "棕榈木台阶", zh_tw = "棕櫚木半磚")
    @VanillaTag(names = "wooden_slabs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> PALM_SLAB = register("palm_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB));
    @I18n(en_us = "Palm Stairs", zh_cn = "棕榈木楼梯", zh_tw = "棕櫚木樓梯")
    @VanillaTag(names = "wooden_stairs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> PALM_STAIRS = register("palm_stairs", props -> new StairBlock(PALM_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    @I18n(en_us = "Palm Door", zh_cn = "棕榈木门", zh_tw = "棕櫚木門")
    @VanillaTag(names = "wooden_doors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<DoorBlock> PALM_DOOR = register("palm_door", props -> new DoorBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
    @I18n(en_us = "Palm Trapdoor", zh_cn = "棕榈木活板门", zh_tw = "棕櫚木地板門")
    @VanillaTag(names = "wooden_trapdoors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<TrapDoorBlock> PALM_TRAPDOOR = register("palm_trapdoor", props -> new TrapDoorBlock(ModWoodSettings.PALM.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));
    @I18n(en_us = "Palm Sign", zh_cn = "棕榈木告示牌", zh_tw = "棕櫚木告示牌")
    @VanillaTag(names = "standing_signs", type = TagType.Block)
    public static final DeferredBlock<StandingSignBlock> PALM_SIGN = register("palm_sign", props -> new StandingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
    @I18n(en_us = "Palm Wall Sign", zh_cn = "墙上的棕榈木告示牌", zh_tw = "牆上的棕櫚木告示牌", descriptionId = "block.wild_wind.palm_wall_sign")
    @VanillaTag(names = "wall_signs", type = TagType.Block)
    public static final DeferredBlock<WallSignBlock> PALM_WALL_SIGN = register("palm_wall_sign", props -> new WallSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_SIGN).lootFrom(PALM_SIGN));
    @I18n(en_us = "Palm Hanging Sign", zh_cn = "棕榈木悬挂式告示牌", zh_tw = "棕櫚木懸挂式告示牌")
    @VanillaTag(names = "ceiling_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<CeilingHangingSignBlock> PALM_HANGING_SIGN = register("palm_hanging_sign", props -> new CeilingHangingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN));
    @I18n(en_us = "Palm Wall Hanging Sign", zh_cn = "墙上的棕榈木悬挂式告示牌", zh_tw = "牆上的棕櫚木懸挂式告示牌", descriptionId = "block.wild_wind.palm_wall_hanging_sign")
    @VanillaTag(names = "wall_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<WallHangingSignBlock> PALM_WALL_HANGING_SIGN = register("palm_wall_hanging_sign", props -> new WallHangingSignBlock(ModWoodSettings.PALM.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WALL_HANGING_SIGN).lootFrom(PALM_HANGING_SIGN));
    public static final DeferredItem<SignItem> PALM_SIGN_ITEM =
            registerSign("palm_sign", PALM_SIGN, PALM_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> PALM_HANGING_SIGN_ITEM =
            registerHangingSign("palm_hanging_sign", PALM_HANGING_SIGN, PALM_WALL_HANGING_SIGN);

    @I18n(en_us = "Palm Crown", zh_cn = "棕榈树冠", zh_tw = "棕櫚樹冠")
    @BasicBlock
    @VanillaTag(names = "mineable/axe", type = TagType.Block)
    public static final DeferredBlock<Block> PALM_CROWN = register("palm_crown", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));

    @AllWood
    @I18n(en_us = "Baobab Planks", zh_cn = "猴面包木板", zh_tw = "猴麵包木材")
    @VanillaTag(names = "planks", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> BAOBAB_PLANKS = register("baobab_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    @I18n(en_us = "Baobab Log", zh_cn = "猴面包原木", zh_tw = "猴麵包原木")
    @CTag(names = "baobab_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> BAOBAB_LOG = register("baobab_log", p -> new StrippableLog(p, ModBlocks.STRIPPED_BAOBAB_LOG), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LOG));
    @I18n(en_us = "Stripped Baobab Log", zh_cn = "去皮猴面包原木", zh_tw = "剝皮猴麵包原木")
    @CTag(names = "baobab_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_LOG = register("stripped_baobab_log", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG));
    @I18n(en_us = "Baobab Wood", zh_cn = "猴面包木", zh_tw = "猴麵包木塊")
    @CTag(names = "baobab_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> BAOBAB_WOOD = register("baobab_wood", p -> new StrippableLog(p, ModBlocks.STRIPPED_BAOBAB_WOOD), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD));
    @I18n(en_us = "Stripped Baobab Wood", zh_cn = "去皮猴面包木", zh_tw = "剝皮猴麵包木塊")
    @CTag(names = "baobab_logs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BAOBAB_WOOD = register("stripped_baobab_wood", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_WOOD));
    @I18n(en_us = "Baobab Button", zh_cn = "猴面包木按钮", zh_tw = "猴麵包木按鈕")
    @VanillaTag(names = "wooden_buttons", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<ButtonBlock> BAOBAB_BUTTON = register("baobab_button", props -> new ButtonBlock(ModWoodSettings.BAOBAB.setType, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_BUTTON));
    @I18n(en_us = "Baobab Fence", zh_cn = "猴面包木栅栏", zh_tw = "猴麵包木柵欄")
    @VanillaTag(names = "wooden_fences", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceBlock> BAOBAB_FENCE = register("baobab_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE));
    @I18n(en_us = "Baobab Fence Gate", zh_cn = "猴面包木栅栏门", zh_tw = "猴麵包木柵欄門")
    @VanillaTag(names = "fence_gates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<FenceGateBlock> BAOBAB_FENCE_GATE = register("baobab_fence_gate", props -> new FenceGateBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE));
    @I18n(en_us = "Baobab Pressure Plate", zh_cn = "猴面包木压力板", zh_tw = "猴麵包木压力板")
    @VanillaTag(names = "wooden_pressure_plates", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<PressurePlateBlock> BAOBAB_PRESSURE_PLATE = register("baobab_pressure_plate", props -> new PressurePlateBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PRESSURE_PLATE));
    @I18n(en_us = "Baobab Slab", zh_cn = "猴面包木台阶", zh_tw = "猴麵包木半磚")
    @VanillaTag(names = "wooden_slabs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> BAOBAB_SLAB = register("baobab_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));
    @I18n(en_us = "Baobab Stairs", zh_cn = "猴面包木楼梯", zh_tw = "猴麵包木樓梯")
    @VanillaTag(names = "wooden_stairs", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> BAOBAB_STAIRS = register("baobab_stairs",
            props -> new StairBlock(BAOBAB_PLANKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS));
    @I18n(en_us = "Baobab Door", zh_cn = "猴面包木门", zh_tw = "猴麵包木門")
    @VanillaTag(names = "wooden_doors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<DoorBlock> BAOBAB_DOOR = register("baobab_door",
            props -> new DoorBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR));
    @I18n(en_us = "Baobab Trapdoor", zh_cn = "猴面包木活板门", zh_tw = "猴麵包木地板門")
    @VanillaTag(names = "wooden_trapdoors", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<TrapDoorBlock> BAOBAB_TRAPDOOR = register("baobab_trapdoor",
            props -> new TrapDoorBlock(ModWoodSettings.BAOBAB.setType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR));
    @I18n(en_us = "Baobab Sign", zh_cn = "猴面包木告示牌", zh_tw = "猴麵包木告示牌")
    @VanillaTag(names = "standing_signs", type = TagType.Block)
    public static final DeferredBlock<StandingSignBlock> BAOBAB_SIGN = register("baobab_sign",
            props -> new StandingSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SIGN));
    @I18n(en_us = "Baobab Wall Sign", zh_cn = "墙上的猴面包木告示牌", zh_tw = "牆上的猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_sign")
    @VanillaTag(names = "wall_signs", type = TagType.Block)
    public static final DeferredBlock<WallSignBlock> BAOBAB_WALL_SIGN = register("baobab_wall_sign",
            props -> new WallSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_SIGN).lootFrom(BAOBAB_SIGN));
    @I18n(en_us = "Baobab Hanging Sign", zh_cn = "悬挂式猴面包木告示牌", zh_tw = "懸挂式猴麵包木告示牌")
    @VanillaTag(names = "ceiling_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<CeilingHangingSignBlock> BAOBAB_HANGING_SIGN = register("baobab_hanging_sign",
            props -> new CeilingHangingSignBlock(ModWoodSettings.BAOBAB.woodType, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_HANGING_SIGN));
    @I18n(en_us = "Baobab Wall Hanging Sign", zh_cn = "墙上的悬挂式猴面包木告示牌", zh_tw = "牆上的懸挂式猴麵包木告示牌", descriptionId = "block.wild_wind.baobab_wall_hanging_sign")
    @VanillaTag(names = "wall_hanging_signs", type = TagType.Block)
    public static final DeferredBlock<WallHangingSignBlock> BAOBAB_WALL_HANGING_SIGN = register("baobab_wall_hanging_sign",
            props -> new WallHangingSignBlock(ModWoodSettings.BAOBAB.woodType, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WALL_HANGING_SIGN).lootFrom(BAOBAB_HANGING_SIGN));
    public static final DeferredItem<SignItem> BAOBAB_SIGN_ITEM = registerSign("baobab_sign", BAOBAB_SIGN, BAOBAB_WALL_SIGN);
    public static final DeferredItem<HangingSignItem> BAOBAB_HANGING_SIGN_ITEM = registerHangingSign("baobab_hanging_sign", BAOBAB_HANGING_SIGN, BAOBAB_WALL_HANGING_SIGN);

    @I18n(en_us = "Palm Leaves", zh_cn = "棕榈树叶", zh_tw = "棕櫚樹葉")
    @BasicBlock
    @VanillaTag(names = "leaves", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<LeavesBlock> PALM_LEAVES = register("palm_leaves", LeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    @I18n(en_us = "Baobab Leaves", zh_cn = "猴面包树叶", zh_tw = "猴麵包樹葉")
    @CubeAll
    @VanillaTag(names = "leaves", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<LeavesBlock> BAOBAB_LEAVES = register("baobab_leaves", LeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LEAVES));

    //TODO: TreeGrower
    @I18n(en_us = "Palm Sapling", zh_cn = "棕榈树苗", zh_tw = "棕櫚樹苗")
    @Cross(item = false)
    @BasicBlockLocatedItem
    @VanillaTag(names = "saplings", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SaplingBlock> PALM_SAPLING = register("palm_sapling", props -> new SaplingBlock(ModTreeGrowers.PALM, props), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));

    @I18n(en_us = "Baobab Sapling", zh_cn = "猴面包树苗", zh_tw = "猴麵包樹苗")
    @Cross(item = false)
    @VanillaTag(names = "saplings", type = TagType.Block)
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<SaplingBlock> BAOBAB_SAPLING = register("baobab_sapling", props -> new SaplingBlock(ModTreeGrowers.BAOBAB, props), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING));

    @AllBrick
    @I18n(en_us = "Andesite Bricks", zh_cn = "安山岩砖", zh_tw = "安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> ANDESITE_BRICKS = register("andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Cracked Andesite Bricks", zh_cn = "裂纹安山岩砖", zh_tw = "裂紋安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_ANDESITE_BRICKS = register("cracked_andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Stairs", zh_cn = "安山岩砖楼梯", zh_tw = "安山岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> ANDESITE_BRICK_STAIRS = register("andesite_brick_stairs", props -> new StairBlock(ANDESITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Slab", zh_cn = "安山岩砖台阶", zh_tw = "安山岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> ANDESITE_BRICK_SLAB = register("andesite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.STONE));
    @I18n(en_us = "Andesite Brick Wall", zh_cn = "安山岩砖墙", zh_tw = "安山岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> ANDESITE_BRICK_WALL = register("andesite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.STONE));
    @I18n(en_us = "Chiseled Andesite Bricks", zh_cn = "雕纹安山岩砖", zh_tw = "浮雕安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_andesite_bricks", end = "wild_wind:block/chiseled_andesite_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_ANDESITE_BRICKS = register("chiseled_andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));

    @AllBrick
    @I18n(en_us = "Diorite Bricks", zh_cn = "闪长岩砖", zh_tw = "閃長岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> DIORITE_BRICKS = register("diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Cracked Diorite Bricks", zh_cn = "裂纹闪长岩砖", zh_tw = "裂紋閃長岩磚")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_DIORITE_BRICKS = register("cracked_diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Stairs", zh_cn = "闪长岩砖楼梯", zh_tw = "閃長岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> DIORITE_BRICK_STAIRS = register("diorite_brick_stairs", props -> new StairBlock(DIORITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Slab", zh_cn = "闪长岩砖台阶", zh_tw = "閃長岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> DIORITE_BRICK_SLAB = register("diorite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Diorite Brick Wall", zh_cn = "闪长岩砖墙", zh_tw = "閃長岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> DIORITE_BRICK_WALL = register("diorite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Chiseled Diorite Bricks", zh_cn = "雕纹闪长岩砖", zh_tw = "浮雕閃長岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_diorite_bricks", end = "wild_wind:block/chiseled_diorite_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_DIORITE_BRICKS = register("chiseled_diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));

    @AllBrick
    @I18n(en_us = "Granite Bricks", zh_cn = "花岗岩砖", zh_tw = "花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> GRANITE_BRICKS = register("granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Cracked Granite Bricks", zh_cn = "裂纹花岗岩砖", zh_tw = "裂紋花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_GRANITE_BRICKS = register("cracked_granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Stairs", zh_cn = "花岗岩砖楼梯", zh_tw = "花崗岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> GRANITE_BRICK_STAIRS = register("granite_brick_stairs", props -> new StairBlock(GRANITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Slab", zh_cn = "花岗岩砖台阶", zh_tw = "花崗岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> GRANITE_BRICK_SLAB = register("granite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DIRT));
    @I18n(en_us = "Granite Brick Wall", zh_cn = "花岗岩砖墙", zh_tw = "花崗岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> GRANITE_BRICK_WALL = register("granite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DIRT));
    @I18n(en_us = "Chiseled Granite Bricks", zh_cn = "雕纹花岗岩砖", zh_tw = "浮雕花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_granite_bricks", end = "wild_wind:block/chiseled_granite_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_GRANITE_BRICKS = register("chiseled_granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));

    @AllBrick
    @I18n(en_us = "Blue Ice Bricks", zh_cn = "蓝冰砖", zh_tw = "藍冰磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> BLUE_ICE_BRICKS = register("blue_ice_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Cracked Blue Ice Bricks", zh_cn = "裂纹蓝冰砖", zh_tw = "裂紋藍冰磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_BLUE_ICE_BRICKS = register("cracked_blue_ice_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Stairs", zh_cn = "蓝冰砖楼梯", zh_tw = "藍冰磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> BLUE_ICE_BRICK_STAIRS = register("blue_ice_brick_stairs", props -> new StairBlock(BLUE_ICE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Slab", zh_cn = "蓝冰砖台阶", zh_tw = "藍冰磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> BLUE_ICE_BRICK_SLAB = register("blue_ice_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.ICE));
    @I18n(en_us = "Blue Ice Brick Wall", zh_cn = "蓝冰砖墙", zh_tw = "藍冰磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> BLUE_ICE_BRICK_WALL = register("blue_ice_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.ICE));
    @I18n(en_us = "Chiseled Blue Ice Bricks", zh_cn = "雕纹蓝冰砖", zh_tw = "浮雕藍冰磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_blue_ice_bricks",
            end = "wild_wind:block/chiseled_blue_ice_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_BLUE_ICE_BRICKS = register("chiseled_blue_ice_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.ICE));

    @I18n(en_us = "Cracked Tuff Bricks", zh_cn = "裂纹凝灰岩砖", zh_tw = "裂紋凝灰岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_TUFF_BRICKS = register("cracked_tuff_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    @I18n(en_us = "Cracked Bricks", zh_cn = "裂纹红砖块", zh_tw = "裂紋紅磚塊")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_BRICKS = register("cracked_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_RED));
    @I18n(en_us = "Chiseled Bricks", zh_cn = "雕纹红砖块", zh_tw = "浮雕紅磚塊")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_BRICKS = register("chiseled_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_RED));

    @I18n(en_us = "Chiseled Deepslate Bricks", zh_cn = "雕纹深板岩砖", zh_tw = "浮雕深板岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_DEEPSLATE_BRICKS = register("chiseled_deepslate_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Chiseled Deepslate Tiles", zh_cn = "雕纹深板岩瓦", zh_tw = "浮雕深板岩磚瓦")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_deepslate_tiles", end = "wild_wind:block/chiseled_deepslate_tiles_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_DEEPSLATE_TILES = register("chiseled_deepslate_tiles", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DEEPSLATE));

    @I18n(en_us = "Mossy Sandstone", zh_cn = "苔砂岩", zh_tw = "青苔砂岩")
    @CubeBottomTop(side = "wild_wind:block/mossy_sandstone", bottom = "wild_wind:block/mossy_sandstone_bottom", top = "minecraft:block/sandstone_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlockItem
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_SANDSTONE = register("mossy_sandstone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(1.5F, 3.0F));
    @I18n(en_us = "Mossy Sandstone Wall", zh_cn = "苔砂岩墙", zh_tw = "青苔砂岩牆")
    @Wall(wall = "wild_wind:block/mossy_sandstone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> MOSSY_SANDSTONE_WALL = register("mossy_sandstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.MOSSY_SANDSTONE.get())), EMPTY);
    @I18n(en_us = "Mossy Sandstone Stairs", zh_cn = "苔砂岩楼梯", zh_tw = "青苔砂岩樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_sandstone_bottom", top = "minecraft:block/sandstone_top", side = "wild_wind:block/mossy_sandstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_SANDSTONE_STAIRS = register("mossy_sandstone_stairs", properties -> new StairBlock(ModBlocks.MOSSY_SANDSTONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.SAND).strength(1.5F, 3.0F));
    @I18n(en_us = "Mossy Sandstone Slab", zh_cn = "苔砂岩台阶", zh_tw = "青苔砂岩半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/mossy_sandstone_bottom", side = "wild_wind:block/mossy_sandstone", top = "minecraft:block/sandstone_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_SANDSTONE_SLAB = register("mossy_sandstone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.SAND).strength(1.5F, 3.0F));
    @I18n(en_us = "Smooth Sandstone Wall", zh_cn = "平滑砂岩墙", zh_tw = "平滑砂岩牆")
    @Wall(wall = "minecraft:block/sandstone_top")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> SMOOTH_SANDSTONE_WALL = register("smooth_sandstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)), EMPTY);

    @I18n(en_us = "Mossy Red Sandstone", zh_cn = "苔红砂岩", zh_tw = "青苔紅砂岩")
    @CubeBottomTop(side = "wild_wind:block/mossy_red_sandstone", bottom = "wild_wind:block/mossy_red_sandstone_bottom", top = "block/red_sandstone_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlockItem
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_RED_SANDSTONE = register("mossy_red_sandstone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 3.0F));
    @I18n(en_us = "Mossy Red Sandstone Wall", zh_cn = "苔红砂岩墙", zh_tw = "青苔紅砂岩牆")
    @Wall(wall = "wild_wind:block/mossy_red_sandstone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> MOSSY_RED_SANDSTONE_WALL = register("mossy_red_sandstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.MOSSY_RED_SANDSTONE.get())), EMPTY);
    @I18n(en_us = "Mossy Red Sandstone Stairs", zh_cn = "苔红砂岩楼梯", zh_tw = "青苔紅砂岩樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_red_sandstone_bottom", top = "block/red_sandstone_top", side = "wild_wind:block/mossy_red_sandstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_RED_SANDSTONE_STAIRS = register("mossy_red_sandstone_stairs", properties -> new StairBlock(ModBlocks.MOSSY_RED_SANDSTONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 3.0F));
    @I18n(en_us = "Mossy Red Sandstone Slab", zh_cn = "苔红砂岩台阶", zh_tw = "青苔紅砂岩半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/mossy_red_sandstone_bottom", side = "wild_wind:block/mossy_red_sandstone", top = "block/red_sandstone_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_RED_SANDSTONE_SLAB = register("mossy_red_sandstone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 3.0F));
    @I18n(en_us = "Smooth Red Sandstone Wall", zh_cn = "平滑红砂岩墙", zh_tw = "平滑紅砂岩牆")
    @Wall(wall = "minecraft:block/red_sandstone_top")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> SMOOTH_RED_SANDSTONE_WALL = register("smooth_red_sandstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE)), EMPTY);

    @I18n(en_us = "Cracked Mud Bricks", zh_cn = "裂纹泥砖", zh_tw = "裂紋泥磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_MUD_BRICKS = register("cracked_mud_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Chiseled Mud Bricks", zh_cn = "雕纹泥砖", zh_tw = "浮雕泥磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_mud_bricks",
            end = "wild_wind:block/chiseled_mud_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_MUD_BRICKS = register("chiseled_mud_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));

    @I18n(en_us = "Cobbled Blackstone", zh_cn = "黑石圆石", zh_tw = "黑石碎石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> COBBLED_BLACKSTONE = register("cobbled_blackstone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.5f));
    @I18n(en_us = "Cobbled Blackstone Wall", zh_cn = "黑石圆石墙", zh_tw = "黑石碎石牆")
    @Wall(wall = "wild_wind:block/cobbled_blackstone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> COBBLED_BLACKSTONE_WALL = register("cobbled_blackstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.COBBLED_BLACKSTONE.get())), EMPTY);
    @I18n(en_us = "Cobbled Blackstone Stairs", zh_cn = "黑石圆石楼梯", zh_tw = "黑石碎石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/cobbled_blackstone", top = "wild_wind:block/cobbled_blackstone", side = "wild_wind:block/cobbled_blackstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> COBBLED_BLACKSTONE_STAIRS = register("cobbled_blackstone_stairs", properties -> new StairBlock(ModBlocks.COBBLED_BLACKSTONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    @I18n(en_us = "Cobbled Blackstone Slab", zh_cn = "黑石圆石台阶", zh_tw = "黑石碎石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/cobbled_blackstone", side = "wild_wind:block/cobbled_blackstone", top = "wild_wind:block/cobbled_blackstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> COBBLED_BLACKSTONE_SLAB = register("cobbled_blackstone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    @I18n(en_us = "Mossy Cobbled Blackstone", zh_cn = "苔黑石圆石", zh_tw = "青苔黑石碎石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_COBBLED_BLACKSTONE = register("mossy_cobbled_blackstone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(2.5f));
    @I18n(en_us = "Mossy Cobbled Blackstone Wall", zh_cn = "苔黑石圆石墙", zh_tw = "青苔黑石碎石牆")
    @Wall(wall = "wild_wind:block/mossy_cobbled_blackstone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> MOSSY_COBBLED_BLACKSTONE_WALL = register("mossy_cobbled_blackstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.MOSSY_COBBLED_BLACKSTONE.get())), EMPTY);
    @I18n(en_us = "Mossy Cobbled Blackstone Stairs", zh_cn = "苔黑石圆石楼梯", zh_tw = "青苔黑石碎石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_cobbled_blackstone", top = "wild_wind:block/mossy_cobbled_blackstone", side = "wild_wind:block/mossy_cobbled_blackstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_COBBLED_BLACKSTONE_STAIRS = register("mossy_cobbled_blackstone_stairs", properties -> new StairBlock(ModBlocks.MOSSY_COBBLED_BLACKSTONE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Cobbled Blackstone Slab", zh_cn = "苔黑石圆石台阶", zh_tw = "青苔黑石碎石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/mossy_cobbled_blackstone", side = "wild_wind:block/mossy_cobbled_blackstone", top = "wild_wind:block/mossy_cobbled_blackstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_COBBLED_BLACKSTONE_SLAB = register("mossy_cobbled_blackstone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Chiseled Polished Blackstone Bricks", zh_cn = "雕纹磨制黑石砖", zh_tw = "浮雕磨製黑石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_POLISHED_BLACKSTONE_BRICKS = register("chiseled_polished_blackstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_BLACK));
    @AllBrick
    @I18n(en_us = "Mossy Polished Blackstone Bricks", zh_cn = "苔磨制黑石砖", zh_tw = "青苔磨製黑石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_POLISHED_BLACKSTONE_BRICKS = register("mossy_polished_blackstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Polished Blackstone Brick Stairs", zh_cn = "苔磨制黑石砖楼梯", zh_tw = "青苔磨製黑石磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_POLISHED_BLACKSTONE_BRICK_STAIRS = register("mossy_polished_blackstone_brick_stairs", props -> new StairBlock(MOSSY_POLISHED_BLACKSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Polished Blackstone Brick Slab", zh_cn = "苔磨制黑石砖台阶", zh_tw = "青苔磨製黑石磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_POLISHED_BLACKSTONE_BRICK_SLAB = register("mossy_polished_blackstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Polished Blackstone Brick Wall", zh_cn = "苔磨制黑石砖墙", zh_tw = "青苔磨製黑石磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_POLISHED_BLACKSTONE_BRICK_WALL = register("mossy_polished_blackstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.COLOR_BLACK));

    @AllBrick
    @I18n(en_us = "Mossy Granite Bricks", zh_cn = "苔花岗岩砖", zh_tw = "青苔花崗岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_GRANITE_BRICKS = register("mossy_granite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Mossy Granite Brick Stairs", zh_cn = "苔花岗岩砖楼梯", zh_tw = "青苔花崗岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_GRANITE_BRICK_STAIRS = register("mossy_granite_brick_stairs", props -> new StairBlock(MOSSY_GRANITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DIRT));
    @I18n(en_us = "Mossy Granite Brick Slab", zh_cn = "苔花岗岩砖台阶", zh_tw = "青苔花崗岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_GRANITE_BRICK_SLAB = register("mossy_granite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DIRT));
    @I18n(en_us = "Mossy Granite Brick Wall", zh_cn = "苔花岗岩砖墙", zh_tw = "青苔花崗岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_GRANITE_BRICK_WALL = register("mossy_granite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DIRT));
    @AllBrick
    @I18n(en_us = "Mossy Diorite Bricks", zh_cn = "苔闪长岩砖", zh_tw = "青苔閃長岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DIORITE_BRICKS = register("mossy_diorite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Diorite Brick Stairs", zh_cn = "苔闪长岩砖楼梯", zh_tw = "青苔閃長岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DIORITE_BRICK_STAIRS = register("mossy_diorite_brick_stairs", props -> new StairBlock(MOSSY_DIORITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Diorite Brick Slab", zh_cn = "苔闪长岩砖台阶", zh_tw = "青苔閃長岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DIORITE_BRICK_SLAB = register("mossy_diorite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Diorite Brick Wall", zh_cn = "苔闪长岩砖墙", zh_tw = "青苔閃長岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DIORITE_BRICK_WALL = register("mossy_diorite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.QUARTZ));
    @AllBrick
    @I18n(en_us = "Mossy Andesite Bricks", zh_cn = "苔安山岩砖", zh_tw = "青苔安山岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_ANDESITE_BRICKS = register("mossy_andesite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.STONE));
    @I18n(en_us = "Mossy Andesite Brick Stairs", zh_cn = "苔安山岩砖楼梯", zh_tw = "青苔安山岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_ANDESITE_BRICK_STAIRS = register("mossy_andesite_brick_stairs", props -> new StairBlock(MOSSY_ANDESITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.STONE));
    @I18n(en_us = "Mossy Andesite Brick Slab", zh_cn = "苔安山岩砖台阶", zh_tw = "青苔安山岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_ANDESITE_BRICK_SLAB = register("mossy_andesite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.STONE));
    @I18n(en_us = "Mossy Andesite Brick Wall", zh_cn = "苔安山岩砖墙", zh_tw = "青苔安山岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_ANDESITE_BRICK_WALL = register("mossy_andesite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.STONE));
    @I18n(en_us = "Mossy Cobbled Deepslate", zh_cn = "苔深板岩圆石", zh_tw = "青苔深板岩碎石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_COBBLED_DEEPSLATE = register("mossy_cobbled_deepslate", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Cobbled Deepslate Stairs", zh_cn = "苔深板岩圆石楼梯", zh_tw = "青苔碎深板岩樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_cobbled_deepslate", top = "wild_wind:block/mossy_cobbled_deepslate", side = "wild_wind:block/mossy_cobbled_deepslate")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_COBBLED_DEEPSLATE_STAIRS = register("mossy_cobbled_deepslate_stairs", props -> new StairBlock(MOSSY_COBBLED_DEEPSLATE.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Cobbled Deepslate Slab", zh_cn = "苔深板岩圆石台阶", zh_tw = "青苔碎深板岩臺階")
    @Slab(type = "stone", bottom = "wild_wind:block/mossy_cobbled_deepslate", side = "wild_wind:block/mossy_cobbled_deepslate", top = "wild_wind:block/mossy_cobbled_deepslate")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_COBBLED_DEEPSLATE_SLAB = register("mossy_cobbled_deepslate_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Cobbled Deepslate Wall", zh_cn = "苔深板岩圆石墙", zh_tw = "青苔碎深板岩墻")
    @Wall(wall = "wild_wind:block/mossy_cobbled_deepslate")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_COBBLED_DEEPSLATE_WALL = register("mossy_cobbled_deepslate_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DEEPSLATE));
    @AllBrick
    @I18n(en_us = "Mossy Deepslate Bricks", zh_cn = "苔深板岩砖", zh_tw = "青苔深板岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_BRICKS = register("mossy_deepslate_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Brick Stairs", zh_cn = "苔深板岩砖楼梯", zh_tw = "青苔深板岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DEEPSLATE_BRICK_STAIRS = register("mossy_deepslate_brick_stairs", props -> new StairBlock(MOSSY_DEEPSLATE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Brick Slab", zh_cn = "苔深板岩砖台阶", zh_tw = "青苔深板岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DEEPSLATE_BRICK_SLAB = register("mossy_deepslate_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Brick Wall", zh_cn = "苔深板岩砖墙", zh_tw = "青苔深板岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DEEPSLATE_BRICK_WALL = register("mossy_deepslate_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DEEPSLATE));
    @AllBrick
    @I18n(en_us = "Mossy Deepslate Tiles", zh_cn = "苔深板岩瓦", zh_tw = "青苔深板岩磚瓦")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DEEPSLATE_TILES = register("mossy_deepslate_tiles", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Tile Stairs", zh_cn = "苔深板岩瓦楼梯", zh_tw = "青苔深板岩磚瓦樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DEEPSLATE_TILE_STAIRS = register("mossy_deepslate_tile_stairs", props -> new StairBlock(MOSSY_DEEPSLATE_TILES.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Tile Slab", zh_cn = "苔深板岩瓦台阶", zh_tw = "青苔深板岩磚瓦臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DEEPSLATE_TILE_SLAB = register("mossy_deepslate_tile_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.DEEPSLATE));
    @I18n(en_us = "Mossy Deepslate Tile Wall", zh_cn = "苔深板瓦砖墙", zh_tw = "青苔深板岩磚瓦墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DEEPSLATE_TILE_WALL = register("mossy_deepslate_tile_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.DEEPSLATE));
    @AllBrick
    @I18n(en_us = "Mossy Tuff Bricks", zh_cn = "苔凝灰岩砖", zh_tw = "青苔凝灰岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_TUFF_BRICKS = register("mossy_tuff_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_GRAY));
    @I18n(en_us = "Mossy Tuff Brick Stairs", zh_cn = "苔凝灰岩砖楼梯", zh_tw = "青苔凝灰岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_TUFF_BRICK_STAIRS = register("mossy_tuff_brick_stairs", props -> new StairBlock(MOSSY_TUFF_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_GRAY));
    @I18n(en_us = "Mossy Tuff Brick Slab", zh_cn = "苔凝灰岩砖台阶", zh_tw = "青苔凝灰岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_TUFF_BRICK_SLAB = register("mossy_tuff_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_GRAY));
    @I18n(en_us = "Mossy Tuff Brick Wall", zh_cn = "苔凝灰岩砖墙", zh_tw = "青苔凝灰岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_TUFF_BRICK_WALL = register("mossy_tuff_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_GRAY));
    @AllBrick
    @I18n(en_us = "Mossy Bricks", zh_cn = "苔红砖", zh_tw = "青苔紅磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_BRICKS = register("mossy_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_RED));
    @I18n(en_us = "Mossy Brick Stairs", zh_cn = "苔红砖楼梯", zh_tw = "青苔紅磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_BRICK_STAIRS = register("mossy_brick_stairs", props -> new StairBlock(MOSSY_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.COLOR_RED));
    @I18n(en_us = "Mossy Brick Slab", zh_cn = "苔红砖台阶", zh_tw = "青苔紅磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_BRICK_SLAB = register("mossy_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.COLOR_RED));
    @I18n(en_us = "Mossy Brick Wall", zh_cn = "苔红砖墙", zh_tw = "青苔紅磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_BRICK_WALL = register("mossy_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.COLOR_RED));
    @AllBrick
    @I18n(en_us = "Mossy Mud Bricks", zh_cn = "苔泥砖", zh_tw = "青苔泥磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_MUD_BRICKS = register("mossy_mud_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    @I18n(en_us = "Mossy Mud Brick Stairs", zh_cn = "苔泥砖楼梯", zh_tw = "青苔泥磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_MUD_BRICK_STAIRS = register("mossy_mud_brick_stairs", props -> new StairBlock(MOSSY_MUD_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    @I18n(en_us = "Mossy Mud Brick Slab", zh_cn = "苔泥砖台阶", zh_tw = "青苔泥磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_MUD_BRICK_SLAB = register("mossy_mud_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    @I18n(en_us = "Mossy Mud Brick Wall", zh_cn = "苔泥砖墙", zh_tw = "青苔泥磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_MUD_BRICK_WALL = register("mossy_mud_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
    @AllBrick
    @I18n(en_us = "Sandstone Bricks", zh_cn = "砂岩砖", zh_tw = "砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> SANDSTONE_BRICKS = register("sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Sandstone Brick Stairs", zh_cn = "砂岩砖楼梯", zh_tw = "砂岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> SANDSTONE_BRICK_STAIRS = register("sandstone_brick_stairs", props -> new StairBlock(SANDSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.SAND));
    @I18n(en_us = "Sandstone Brick Slab", zh_cn = "砂岩砖台阶", zh_tw = "砂岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> SANDSTONE_BRICK_SLAB = register("sandstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.SAND));
    @I18n(en_us = "Sandstone Brick Wall", zh_cn = "砂岩砖墙", zh_tw = "砂岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> SANDSTONE_BRICK_WALL = register("sandstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.SAND));
    @I18n(en_us = "Cracked Sandstone Bricks", zh_cn = "裂纹砂岩砖", zh_tw = "裂紋砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_SANDSTONE_BRICKS = register("cracked_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Chiseled Sandstone Bricks", zh_cn = "雕纹砂岩砖", zh_tw = "浮雕砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_sandstone_bricks",
            end = "wild_wind:block/chiseled_sandstone_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_SANDSTONE_BRICKS = register("chiseled_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SAND));
    @AllBrick
    @I18n(en_us = "Red Sandstone Bricks", zh_cn = "红砂岩砖", zh_tw = "紅砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> RED_SANDSTONE_BRICKS = register("red_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_ORANGE));
    @I18n(en_us = "Red Sandstone Brick Stairs", zh_cn = "红砂岩砖楼梯", zh_tw = "紅砂岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> RED_SANDSTONE_BRICK_STAIRS = register("red_sandstone_brick_stairs", props -> new StairBlock(RED_SANDSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.COLOR_ORANGE));
    @I18n(en_us = "Red Sandstone Brick Slab", zh_cn = "红砂岩砖台阶", zh_tw = "紅砂岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> RED_SANDSTONE_BRICK_SLAB = register("red_sandstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.COLOR_ORANGE));
    @I18n(en_us = "Red Sandstone Brick Wall", zh_cn = "红砂岩砖墙", zh_tw = "紅砂岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> RED_SANDSTONE_BRICK_WALL = register("red_sandstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.COLOR_ORANGE));
    @I18n(en_us = "Cracked Red Sandstone Bricks", zh_cn = "裂纹红砂岩砖", zh_tw = "裂紋紅砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_RED_SANDSTONE_BRICKS = register("cracked_red_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_ORANGE));
    @I18n(en_us = "Chiseled Red Sandstone Bricks", zh_cn = "雕纹红砂岩砖", zh_tw = "浮雕紅砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_red_sandstone_bricks",
            end = "wild_wind:block/chiseled_red_sandstone_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_RED_SANDSTONE_BRICKS = register("chiseled_red_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_ORANGE));
    @AllBrick
    @I18n(en_us = "Mossy Sandstone Bricks", zh_cn = "苔砂岩砖", zh_tw = "青苔砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_SANDSTONE_BRICKS = register("mossy_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy Sandstone Brick Stairs", zh_cn = "苔砂岩砖楼梯", zh_tw = "青苔砂岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_SANDSTONE_BRICK_STAIRS = register("mossy_sandstone_brick_stairs", props -> new StairBlock(MOSSY_SANDSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy Sandstone Brick Slab", zh_cn = "苔砂岩砖台阶", zh_tw = "青苔砂岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_SANDSTONE_BRICK_SLAB = register("mossy_sandstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy Sandstone Brick Wall", zh_cn = "苔砂岩砖墙", zh_tw = "青苔砂岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_SANDSTONE_BRICK_WALL = register("mossy_sandstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.SAND));
    @AllBrick
    @I18n(en_us = "Mossy Red Sandstone Bricks", zh_cn = "苔红砂岩砖", zh_tw = "青苔紅砂岩磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_RED_SANDSTONE_BRICKS = register("mossy_red_sandstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_ORANGE));
    @I18n(en_us = "Mossy Red Sandstone Brick Stairs", zh_cn = "苔红砂岩砖楼梯", zh_tw = "青苔紅砂岩磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_RED_SANDSTONE_BRICK_STAIRS = register("mossy_red_sandstone_brick_stairs", props -> new StairBlock(MOSSY_RED_SANDSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_ORANGE));
    @I18n(en_us = "Mossy Red Sandstone Brick Slab", zh_cn = "苔红砂岩砖台阶", zh_tw = "青苔紅砂岩磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_RED_SANDSTONE_BRICK_SLAB = register("mossy_red_sandstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_ORANGE));
    @I18n(en_us = "Mossy Red Sandstone Brick Wall", zh_cn = "苔红砂岩砖墙", zh_tw = "青苔紅砂岩磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_RED_SANDSTONE_BRICK_WALL = register("mossy_red_sandstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_ORANGE));
    @I18n(en_us = "Mossy Prismarine", zh_cn = "苔海晶石", zh_tw = "青苔海磷石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_PRISMARINE = register("mossy_prismarine", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Stairs", zh_cn = "苔海晶石楼梯", zh_tw = "青苔海磷石樓梯")
    @Stairs(all = "wild_wind:block/mossy_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_PRISMARINE_STAIRS = register("mossy_prismarine_stairs", props -> new StairBlock(MOSSY_PRISMARINE.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Slab", zh_cn = "苔海晶石台阶", zh_tw = "青苔海磷石臺階")
    @Slab(type = "stone", all = "wild_wind:block/mossy_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_PRISMARINE_SLAB = register("mossy_prismarine_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Wall", zh_cn = "苔海晶石墙", zh_tw = "青苔海磷石墻")
    @Wall(wall = "wild_wind:block/mossy_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_PRISMARINE_WALL = register("mossy_prismarine_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Prismarine", zh_cn = "磨制海晶石", zh_tw = "磨製海磷石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_PRISMARINE = register("polished_prismarine", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5f));
    @I18n(en_us = "Polished Prismarine Wall", zh_cn = "磨制海晶石墙", zh_tw = "磨製海磷石牆")
    @Wall(wall = "wild_wind:block/polished_prismarine")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> POLISHED_PRISMARINE_WALL = register("polished_prismarine_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.POLISHED_PRISMARINE.get())), EMPTY);
    @I18n(en_us = "Polished Prismarine Stairs", zh_cn = "磨制海晶石楼梯", zh_tw = "磨製海磷石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_prismarine", top = "wild_wind:block/polished_prismarine", side = "wild_wind:block/polished_prismarine")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_PRISMARINE_STAIRS = register("polished_prismarine_stairs", properties -> new StairBlock(ModBlocks.POLISHED_PRISMARINE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Prismarine Slab", zh_cn = "磨制海晶石台阶", zh_tw = "磨製海磷石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_prismarine", side = "wild_wind:block/polished_prismarine", top = "wild_wind:block/polished_prismarine")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_PRISMARINE_SLAB = register("polished_prismarine_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Cracked Prismarine Bricks", zh_cn = "裂纹海晶石砖", zh_tw = "裂紋海磷石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_PRISMARINE_BRICKS = register("cracked_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Chiseled Prismarine Bricks", zh_cn = "雕纹海晶石砖", zh_tw = "浮雕海磷石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_prismarine_bricks",
            end = "wild_wind:block/chiseled_prismarine_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_PRISMARINE_BRICKS = register("chiseled_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.COLOR_CYAN));

    @AllBrick
    @I18n(en_us = "Snow Bricks", zh_cn = "雪砖", zh_tw = "雪磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> SNOW_BRICKS = register("snow_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SNOW));
    @I18n(en_us = "Cracked Snow Bricks", zh_cn = "裂纹雪砖", zh_tw = "裂紋雪磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_SNOW_BRICKS = register("cracked_snow_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SNOW));
    @I18n(en_us = "Snow Brick Stairs", zh_cn = "雪砖楼梯", zh_tw = "雪磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> SNOW_BRICK_STAIRS = register("snow_brick_stairs", props -> new StairBlock(SNOW_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.SNOW));
    @I18n(en_us = "Snow Brick Slab", zh_cn = "雪砖台阶", zh_tw = "雪磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> SNOW_BRICK_SLAB = register("snow_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.SNOW));
    @I18n(en_us = "Snow Brick Wall", zh_cn = "雪砖墙", zh_tw = "雪磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> SNOW_BRICK_WALL = register("snow_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.SNOW));
    @I18n(en_us = "Chiseled Snow Bricks", zh_cn = "雕纹雪砖", zh_tw = "浮雕雪磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_snow_bricks",
            end = "wild_wind:block/chiseled_snow_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_SNOW_BRICKS = register("chiseled_snow_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.SNOW));

    @I18n(en_us = "Polished Calcite", zh_cn = "磨制方解石", zh_tw = "磨製方解石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_CALCITE = register("polished_calcite", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.5f));
    @I18n(en_us = "Polished Calcite Wall", zh_cn = "磨制方解石墙", zh_tw = "磨製方解石牆")
    @Wall(wall = "wild_wind:block/polished_calcite")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> POLISHED_CALCITE_WALL = register("polished_calcite_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.POLISHED_CALCITE.get())), EMPTY);
    @I18n(en_us = "Polished Calcite Stairs", zh_cn = "磨制方解石楼梯", zh_tw = "磨製方解石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_calcite", top = "wild_wind:block/polished_calcite", side = "wild_wind:block/polished_calcite")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_CALCITE_STAIRS = register("polished_calcite_stairs", properties -> new StairBlock(ModBlocks.POLISHED_CALCITE.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    @I18n(en_us = "Polished Calcite Slab", zh_cn = "磨制方解石台阶", zh_tw = "磨製方解石半磚")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_calcite", side = "wild_wind:block/polished_calcite", top = "wild_wind:block/polished_calcite")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_CALCITE_SLAB = register("polished_calcite_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));

    @AllBrick
    @I18n(en_us = "Calcite Bricks", zh_cn = "方解石砖", zh_tw = "方解石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CALCITE_BRICKS = register("calcite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Cracked Calcite Bricks", zh_cn = "裂纹方解石砖", zh_tw = "裂紋方解石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_CALCITE_BRICKS = register("cracked_calcite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Calcite Brick Stairs", zh_cn = "方解石砖楼梯", zh_tw = "方解石磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> CALCITE_BRICK_STAIRS = register("calcite_brick_stairs", props -> new StairBlock(CALCITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Calcite Brick Slab", zh_cn = "方解石砖台阶", zh_tw = "方解石磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> CALCITE_BRICK_SLAB = register("calcite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Calcite Brick Wall", zh_cn = "方解石砖墙", zh_tw = "方解石磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> CALCITE_BRICK_WALL = register("calcite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Chiseled Calcite Bricks", zh_cn = "雕纹方解石砖", zh_tw = "浮雕方解石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_calcite_bricks", end = "wild_wind:block/chiseled_calcite_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_CALCITE_BRICKS = register("chiseled_calcite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE));

    @I18n(en_us = "Polished Dripstone Block", zh_cn = "磨制滴水石块", zh_tw = "磨製鐘乳石方塊")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_DRIPSTONE_BLOCK = register("polished_dripstone", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(2.5f));
    @I18n(en_us = "Polished Dripstone Wall", zh_cn = "磨制滴水石墙", zh_tw = "磨製鐘乳石牆")
    @Wall(wall = "wild_wind:block/polished_dripstone")
    @RegistryBlockItem
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    public static final DeferredBlock<WallBlock> POLISHED_DRIPSTONE_WALL = register("polished_dripstone_wall", properties -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.POLISHED_DRIPSTONE_BLOCK.get())), EMPTY);
    @I18n(en_us = "Polished Dripstone Stairs", zh_cn = "磨制滴水石楼梯", zh_tw = "磨製鐘乳石樓梯")
    @Stairs(type = "stone", all = "wild_wind:block/polished_dripstone")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_DRIPSTONE_STAIRS = register("polished_dripstone_stairs", properties -> new StairBlock(ModBlocks.POLISHED_DRIPSTONE_BLOCK.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    @I18n(en_us = "Polished Dripstone Slab", zh_cn = "磨制滴水石台阶", zh_tw = "磨製鐘乳石半磚")
    @Slab(type = "stone", all = "wild_wind:block/polished_dripstone_block")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_DRIPSTONE_SLAB = register("polished_dripstone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));

    @AllBrick
    @I18n(en_us = "Dripstone Bricks", zh_cn = "滴水石砖", zh_tw = "鐘乳石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> DRIPSTONE_BRICKS = register("dripstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Cracked Dripstone Bricks", zh_cn = "裂纹滴水石砖", zh_tw = "裂紋鐘乳石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicBlock
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_DRIPSTONE_BRICKS = register("cracked_dripstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Dripstone Brick Stairs", zh_cn = "滴水石砖楼梯", zh_tw = "鐘乳石磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> DRIPSTONE_BRICK_STAIRS = register("dripstone_brick_stairs", props -> new StairBlock(DRIPSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Dripstone Brick Slab", zh_cn = "滴水石砖台阶", zh_tw = "鐘乳石磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> DRIPSTONE_BRICK_SLAB = register("dripstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Dripstone Brick Wall", zh_cn = "滴水石砖墙", zh_tw = "鐘乳石磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> DRIPSTONE_BRICK_WALL = register("dripstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Chiseled Dripstone Bricks", zh_cn = "雕纹滴水石砖", zh_tw = "浮雕鐘乳石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @CubeColumn(side = "wild_wind:block/chiseled_dripstone_bricks",
            end = "wild_wind:block/chiseled_dripstone_bricks_top")
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_DRIPSTONE_BRICKS = register("chiseled_dripstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN));
    @AllBrick
    @I18n(en_us = "Mossy Calcite Bricks", zh_cn = "苔方解石砖", zh_tw = "青苔方解石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_CALCITE_BRICKS = register("mossy_calcite_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Mossy Calcite Brick Stairs", zh_cn = "苔方解石砖楼梯", zh_tw = "青苔方解石磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_CALCITE_BRICK_STAIRS = register("mossy_calcite_brick_stairs", props -> new StairBlock(MOSSY_CALCITE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Mossy Calcite Brick Slab", zh_cn = "苔方解石砖台阶", zh_tw = "青苔方解石磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_CALCITE_BRICK_SLAB = register("mossy_calcite_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
    @I18n(en_us = "Mossy Calcite Brick Wall", zh_cn = "苔方解石砖墙", zh_tw = "青苔方解石磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_CALCITE_BRICK_WALL = register("mossy_calcite_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_WHITE));
    @AllBrick
    @I18n(en_us = "Mossy Dripstone Bricks", zh_cn = "苔滴水石砖", zh_tw = "青苔鐘乳石磚")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DRIPSTONE_BRICKS = register("mossy_dripstone_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Mossy Dripstone Brick Stairs", zh_cn = "苔滴水石砖楼梯", zh_tw = "青苔鐘乳石磚樓梯")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DRIPSTONE_BRICK_STAIRS = register("mossy_dripstone_brick_stairs", props -> new StairBlock(MOSSY_DRIPSTONE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Mossy Dripstone Brick Slab", zh_cn = "苔滴水石砖台阶", zh_tw = "青苔鐘乳石磚臺階")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DRIPSTONE_BRICK_SLAB = register("mossy_dripstone_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).mapColor(MapColor.TERRACOTTA_BROWN));
    @I18n(en_us = "Mossy Dripstone Brick Wall", zh_cn = "苔滴水石砖墙", zh_tw = "青苔鐘乳石磚墻")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DRIPSTONE_BRICK_WALL = register("mossy_dripstone_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).mapColor(MapColor.TERRACOTTA_BROWN));

    // 末地石系列
    @I18n(en_us = "End Stone Stairs", zh_cn = "末地石楼梯", zh_tw = "終界石樓梯")
    @Stairs(type = "stone", bottom = "minecraft:block/end_stone", top = "minecraft:block/end_stone", side = "minecraft:block/end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> END_STONE_STAIRS = register("end_stone_stairs",
            props -> new StairBlock(Blocks.END_STONE.defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_STAIRS).mapColor(MapColor.SAND));
    @I18n(en_us = "End Stone Slab", zh_cn = "末地石台阶", zh_tw = "終界石臺階")
    @Slab(bottom = "minecraft:block/end_stone", side = "minecraft:block/end_stone", top = "minecraft:block/end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> END_STONE_SLAB = register("end_stone_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB).mapColor(MapColor.SAND));
    @I18n(en_us = "End Stone Wall", zh_cn = "末地石墙", zh_tw = "終界石墻")
    @Wall(wall = "minecraft:block/end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> END_STONE_WALL = register("end_stone_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.SAND));
    @I18n(en_us = "Polished End Stone", zh_cn = "磨制末地石", zh_tw = "拋光終界石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_END_STONE = register("polished_end_stone",
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Polished End Stone Stairs", zh_cn = "磨制末地石楼梯", zh_tw = "拋光終界石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_end_stone", top = "wild_wind:block/polished_end_stone", side = "wild_wind:block/polished_end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_END_STONE_STAIRS = register("polished_end_stone_stairs",
            props -> new StairBlock(POLISHED_END_STONE.get().defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_STAIRS).mapColor(MapColor.SAND));
    @I18n(en_us = "Polished End Stone Slab", zh_cn = "磨制末地石台阶", zh_tw = "拋光終界石臺階")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_end_stone", side = "wild_wind:block/polished_end_stone", top = "wild_wind:block/polished_end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_END_STONE_SLAB = register("polished_end_stone_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB).mapColor(MapColor.SAND));
    @I18n(en_us = "Polished End Stone Wall", zh_cn = "磨制末地石墙", zh_tw = "拋光終界石墻")
    @Wall(wall = "wild_wind:block/polished_end_stone")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_END_STONE_WALL = register("polished_end_stone_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.SAND));
    @I18n(en_us = "Cracked End Stone Bricks", zh_cn = "裂纹末地石砖", zh_tw = "裂紋終界石磚")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_END_STONE_BRICKS = register("cracked_end_stone_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Chiseled End Stone Bricks", zh_cn = "雕纹末地石砖", zh_tw = "浮雕終界石磚")
    @CubeColumn(side = "wild_wind:block/chiseled_end_stone_bricks", end = "wild_wind:block/chiseled_end_stone_bricks_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_END_STONE_BRICKS = register("chiseled_end_stone_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy End Stone Bricks", zh_cn = "苔末地石砖", zh_tw = "青苔終界石磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_END_STONE_BRICKS = register("mossy_end_stone_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy End Stone Brick Stairs", zh_cn = "苔末地石楼梯", zh_tw = "青苔終界石磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_end_stone_bricks", top = "wild_wind:block/mossy_end_stone_bricks", side = "wild_wind:block/mossy_end_stone_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_END_STONE_BRICK_STAIRS = register("mossy_end_stone_brick_stairs",
            props -> new StairBlock(MOSSY_END_STONE_BRICKS.get().defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_STAIRS).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy End Stone Brick Slab", zh_cn = "苔末地石台阶", zh_tw = "青苔終界石磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_end_stone_bricks", side = "wild_wind:block/mossy_end_stone_bricks", top = "wild_wind:block/mossy_end_stone_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_END_STONE_BRICK_SLAB = register("mossy_end_stone_brick_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB).mapColor(MapColor.SAND));
    @I18n(en_us = "Mossy End Stone Brick Wall", zh_cn = "苔末地石墙", zh_tw = "青苔終界石磚墻")
    @Wall(wall = "wild_wind:block/mossy_end_stone_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_END_STONE_BRICK_WALL = register("mossy_end_stone_brick_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_WALL).mapColor(MapColor.SAND));

    // 石英系列
    @I18n(en_us = "Purpur Wall", zh_cn = "紫珀墙", zh_tw = "紫珀墻")
    @Wall(wall = "minecraft:block/purpur_block")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> PURPUR_WALL = register("purpur_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK).mapColor(MapColor.COLOR_MAGENTA));
    @I18n(en_us = "Quartz Block Wall", zh_cn = "石英块墙", zh_tw = "石英磚墻")
    @Wall(wall = "minecraft:block/quartz_block_side")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> QUARTZ_BLOCK_WALL = register("quartz_block_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Cracked Quartz Bricks", zh_cn = "裂纹石英砖", zh_tw = "裂紋石英磚")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_QUARTZ_BRICKS = register("cracked_quartz_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Quartz Brick Stairs", zh_cn = "石英砖楼梯", zh_tw = "石英磚樓梯")
    @Stairs(type = "stone", bottom = "minecraft:block/quartz_bricks", top = "minecraft:block/quartz_bricks", side = "minecraft:block/quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> QUARTZ_BRICK_STAIRS = register("quartz_brick_stairs",
            props -> new StairBlock(Blocks.QUARTZ_BRICKS.defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Quartz Brick Slab", zh_cn = "石英砖台阶", zh_tw = "石英磚臺階")
    @Slab(type = "bricks", all = "minecraft:block/quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> QUARTZ_BRICK_SLAB = register("quartz_brick_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Quartz Brick Wall", zh_cn = "石英砖墙", zh_tw = "石英磚墻")
    @Wall(wall = "minecraft:block/quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> QUARTZ_BRICK_WALL = register("quartz_brick_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Chiseled Quartz Bricks", zh_cn = "雕纹石英砖", zh_tw = "浮雕石英磚")
    @CubeColumn(side = "wild_wind:block/chiseled_quartz_bricks", end = "wild_wind:block/chiseled_quartz_bricks_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_QUARTZ_BRICKS = register("chiseled_quartz_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_QUARTZ_BLOCK).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Quartz Bricks", zh_cn = "苔石英砖", zh_tw = "青苔石英磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_QUARTZ_BRICKS = register("mossy_quartz_bricks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Quartz Brick Stairs", zh_cn = "苔石英砖楼梯", zh_tw = "青苔石英磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_quartz_bricks", top = "wild_wind:block/mossy_quartz_bricks", side = "wild_wind:block/mossy_quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_QUARTZ_BRICK_STAIRS = register("mossy_quartz_brick_stairs",
            props -> new StairBlock(MOSSY_QUARTZ_BRICKS.get().defaultBlockState(), props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_STAIRS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Quartz Brick Slab", zh_cn = "苔石英砖台阶", zh_tw = "青苔石英磚臺極階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_quartz_bricks", side = "wild_wind:block/mossy_quartz_bricks", top = "wild_wind:block/mossy_quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_QUARTZ_BRICK_SLAB = register("mossy_quartz_brick_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Mossy Quartz Brick Wall", zh_cn = "苔石英砖墙", zh_tw = "青苔石英磚墻")
    @Wall(wall = "wild_wind:block/mossy_quartz_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_QUARTZ_BRICK_WALL = register("mossy_quartz_brick_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).mapColor(MapColor.QUARTZ));
    @I18n(en_us = "Smooth Quartz Wall", zh_cn = "平滑石英墙", zh_tw = "平滑石英墻")
    @Wall(wall = "minecraft:block/quartz_block_bottom")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> SMOOTH_QUARTZ_WALL = register("smooth_quartz_wall",
            WallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ).mapColor(MapColor.QUARTZ));

    // 海晶石系列
    @I18n(en_us = "Mossy Prismarine Bricks", zh_cn = "苔海晶石砖", zh_tw = "青苔海磷石磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_PRISMARINE_BRICKS = register("mossy_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Brick Stairs", zh_cn = "苔海晶石楼梯", zh_tw = "青苔海磷石磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_prismarine_bricks", top = "wild_wind:block/mossy_prismarine_bricks", side = "wild_wind:block/mossy_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_PRISMARINE_BRICK_STAIRS = register("mossy_prismarine_brick_stairs", props -> new StairBlock(MOSSY_PRISMARINE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Brick Slab", zh_cn = "苔海晶石台阶", zh_tw = "青苔海磷石磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_prismarine_bricks", side = "wild_wind:block/mossy_prismarine_bricks", top = "wild_wind:block/mossy_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_PRISMARINE_BRICK_SLAB = register("mossy_prismarine_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Prismarine Brick Wall", zh_cn = "苔海晶石墙", zh_tw = "青苔海磷石磚墻")
    @Wall(wall = "wild_wind:block/mossy_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_PRISMARINE_BRICK_WALL = register("mossy_prismarine_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Ocean Lantern", zh_cn = "暗海晶灯", zh_tw = "深海晶燈")
    @BasicBlock
    @VanillaTag(names = {"mineable/pickaxe", "lanterns"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> OCEAN_LANTERN = register("ocean_lantern", BlockBehaviour.Properties.ofFullCopy(Blocks.SEA_LANTERN).mapColor(MapColor.COLOR_BLUE).lightLevel(state -> 15));

    // 暗海晶石系列
    @I18n(en_us = "Mossy Dark Prismarine", zh_cn = "苔暗海晶石", zh_tw = "青苔暗海磷石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DARK_PRISMARINE = register("mossy_dark_prismarine", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Stairs", zh_cn = "苔暗海晶石楼梯", zh_tw = "青苔暗海磷石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_dark_prismarine", top = "wild_wind:block/mossy_dark_prismarine", side = "wild_wind:block/mossy_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DARK_PRISMARINE_STAIRS = register("mossy_dark_prismarine_stairs", props -> new StairBlock(MOSSY_DARK_PRISMARINE.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Slab", zh_cn = "苔暗海晶石台阶", zh_tw = "青苔暗海磷石臺階")
    @Slab(type = "stone", bottom = "wild_wind:block/mossy_dark_prismarine", side = "wild_wind:block/mossy_dark_prismarine", top = "wild_wind:block/mossy_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DARK_PRISMARINE_SLAB = register("mossy_dark_prismarine_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Wall", zh_cn = "苔暗海晶石墙", zh_tw = "青苔暗海磷石墻")
    @Wall(wall = "wild_wind:block/mossy_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DARK_PRISMARINE_WALL = register("mossy_dark_prismarine_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Dark Prismarine", zh_cn = "磨制暗海晶石", zh_tw = "拋光暗海磷石")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_DARK_PRISMARINE = register("polished_dark_prismarine", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Dark Prismarine Stairs", zh_cn = "磨制暗海晶石楼梯", zh_tw = "拋光暗海磷石樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_dark_prismarine", top = "wild_wind:block/polished_dark_prismarine", side = "wild_wind:block/polished_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_DARK_PRISMARINE_STAIRS = register("polished_dark_prismarine_stairs", props -> new StairBlock(POLISHED_DARK_PRISMARINE.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Dark Prismarine Slab", zh_cn = "磨制暗海晶石台阶", zh_tw = "拋光暗海磷石臺階")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_dark_prismarine", side = "wild_wind:block/polished_dark_prismarine", top = "wild_wind:block/polished_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_DARK_PRISMARINE_SLAB = register("polished_dark_prismarine_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Polished Dark Prismarine Wall", zh_cn = "磨制暗海晶石墙", zh_tw = "拋光暗海磷石墻")
    @Wall(wall = "wild_wind:block/polished_dark_prismarine")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_DARK_PRISMARINE_WALL = register("polished_dark_prismarine_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Dark Prismarine Bricks", zh_cn = "暗海晶石砖", zh_tw = "暗海磷石磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> DARK_PRISMARINE_BRICKS = register("dark_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Cracked Dark Prismarine Bricks", zh_cn = "裂纹暗海晶石砖", zh_tw = "裂紋暗海磷石磚")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_DARK_PRISMARINE_BRICKS = register("cracked_dark_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Dark Prismarine Brick Stairs", zh_cn = "暗海晶石砖楼梯", zh_tw = "暗海磷石磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/dark_prismarine_bricks", top = "wild_wind:block/dark_prismarine_bricks", side = "wild_wind:block/dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> DARK_PRISMARINE_BRICK_STAIRS = register("dark_prismarine_brick_stairs", props -> new StairBlock(DARK_PRISMARINE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Dark Prismarine Brick Slab", zh_cn = "暗海晶石砖台阶", zh_tw = "暗海磷石磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/dark_prismarine_bricks", side = "wild_wind:block/dark_prismarine_bricks", top = "wild_wind:block/dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> DARK_PRISMARINE_BRICK_SLAB = register("dark_prismarine_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Dark Prismarine Brick Wall", zh_cn = "暗海晶石砖墙", zh_tw = "暗海磷石磚墻")
    @Wall(wall = "wild_wind:block/dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> DARK_PRISMARINE_BRICK_WALL = register("dark_prismarine_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Chiseled Dark Prismarine Bricks", zh_cn = "雕纹暗海晶石砖", zh_tw = "浮雕暗海磷石磚")
    @CubeColumn(side = "wild_wind:block/chiseled_dark_prismarine_bricks", end = "wild_wind:block/chiseled_dark_prismarine_bricks_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_DARK_PRISMARINE_BRICKS = register("chiseled_dark_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Bricks", zh_cn = "苔暗海晶石砖", zh_tw = "青苔暗海磷石磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_DARK_PRISMARINE_BRICKS = register("mossy_dark_prismarine_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Brick Stairs", zh_cn = "苔暗海晶石楼梯", zh_tw = "青苔暗海磷石磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_dark_prismarine_bricks", top = "wild_wind:block/mossy_dark_prismarine_bricks", side = "wild_wind:block/mossy_dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_DARK_PRISMARINE_BRICK_STAIRS = register("mossy_dark_prismarine_brick_stairs", props -> new StairBlock(MOSSY_DARK_PRISMARINE_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_STAIRS).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Brick Slab", zh_cn = "苔暗海晶石台阶", zh_tw = "青苔暗海磷石磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_dark_prismarine_bricks", side = "wild_wind:block/mossy_dark_prismarine_bricks", top = "wild_wind:block/mossy_dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_DARK_PRISMARINE_BRICK_SLAB = register("mossy_dark_prismarine_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB).mapColor(MapColor.COLOR_CYAN));
    @I18n(en_us = "Mossy Dark Prismarine Brick Wall", zh_cn = "苔暗海晶石墙", zh_tw = "青苔暗海磷石磚墻")
    @Wall(wall = "wild_wind:block/mossy_dark_prismarine_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_DARK_PRISMARINE_BRICK_WALL = register("mossy_dark_prismarine_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_WALL).mapColor(MapColor.COLOR_CYAN));

    // 下界岩系列
    @I18n(en_us = "Polished Netherrack", zh_cn = "磨制下界岩", zh_tw = "拋光地獄岩")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_NETHERRACK = register("polished_netherrack", BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).mapColor(MapColor.NETHER));
    @I18n(en_us = "Polished Netherrack Stairs", zh_cn = "磨制下界岩楼梯", zh_tw = "拋光地獄岩樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/polished_netherrack", top = "wild_wind:block/polished_netherrack", side = "wild_wind:block/polished_netherrack")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_NETHERRACK_STAIRS = register("polished_netherrack_stairs", props -> new StairBlock(POLISHED_NETHERRACK.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Polished Netherrack Slab", zh_cn = "磨制下界岩台阶", zh_tw = "拋光地獄岩臺階")
    @Slab(type = "stone", bottom = "wild_wind:block/polished_netherrack", side = "wild_wind:block/polished_netherrack", top = "wild_wind:block/polished_netherrack")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_NETHERRACK_SLAB = register("polished_netherrack_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB).mapColor(MapColor.NETHER));
    @I18n(en_us = "Polished Netherrack Wall", zh_cn = "磨制下界岩墙", zh_tw = "拋光地獄岩墻")
    @Wall(wall = "wild_wind:block/polished_netherrack")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_NETHERRACK_WALL = register("polished_netherrack_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Nether Bricks", zh_cn = "苔下界砖块", zh_tw = "青苔地獄磚塊")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_NETHER_BRICKS = register("mossy_nether_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Nether Brick Stairs", zh_cn = "苔下界砖楼梯", zh_tw = "青苔地獄磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_nether_bricks", top = "wild_wind:block/mossy_nether_bricks", side = "wild_wind:block/mossy_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_NETHER_BRICK_STAIRS = register("mossy_nether_brick_stairs", props -> new StairBlock(MOSSY_NETHER_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Nether Brick Slab", zh_cn = "苔下界砖台阶", zh_tw = "青苔地獄磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_nether_bricks", side = "wild_wind:block/mossy_nether_bricks", top = "wild_wind:block/mossy_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_NETHER_BRICK_SLAB = register("mossy_nether_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Nether Brick Wall", zh_cn = "苔下界砖墙", zh_tw = "青苔地獄磚墻")
    @Wall(wall = "wild_wind:block/mossy_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_NETHER_BRICK_WALL = register("mossy_nether_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL).mapColor(MapColor.NETHER));
    @I18n(en_us = "Red Nether Brick Fence", zh_cn = "红色下界砖栅栏", zh_tw = "紅色地獄磚柵欄")
    @VanillaTag(names = {"mineable/pickaxe", "fences"}, type = TagType.Block)
    @Fence(texture = "minecraft:block/red_nether_bricks")//TODO
    @RegistryBlockItem
    public static final DeferredBlock<FenceBlock> RED_NETHER_BRICK_FENCE = register("red_nether_brick_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_FENCE).mapColor(MapColor.NETHER));
    @I18n(en_us = "Cracked Red Nether Bricks", zh_cn = "裂纹红色下界砖块", zh_tw = "裂紋紅色地獄磚塊")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_RED_NETHER_BRICKS = register("cracked_red_nether_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Red Nether Bricks", zh_cn = "苔红色下界砖块", zh_tw = "青苔紅色地獄磚塊")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_RED_NETHER_BRICKS = register("mossy_red_nether_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Red Nether Brick Stairs", zh_cn = "苔红色下界砖楼梯", zh_tw = "青苔紅色地獄磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_red_nether_bricks", top = "wild_wind:block/mossy_red_nether_bricks", side = "wild_wind:block/mossy_red_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_RED_NETHER_BRICK_STAIRS = register("mossy_red_nether_brick_stairs", props -> new StairBlock(MOSSY_RED_NETHER_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_STAIRS).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Red Nether Brick Slab", zh_cn = "苔红色下界砖台阶", zh_tw = "青苔紅色地獄磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_red_nether_bricks", side = "wild_wind:block/mossy_red_nether_bricks", top = "wild_wind:block/mossy_red_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_RED_NETHER_BRICK_SLAB = register("mossy_red_nether_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB).mapColor(MapColor.NETHER));
    @I18n(en_us = "Mossy Red Nether Brick Wall", zh_cn = "苔红色下界砖墙", zh_tw = "青苔紅色地獄磚墻")
    @Wall(wall = "wild_wind:block/mossy_red_nether_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_RED_NETHER_BRICK_WALL = register("mossy_red_nether_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_WALL).mapColor(MapColor.NETHER));
    @I18n(en_us = "Chiseled Red Nether Bricks", zh_cn = "雕纹红色下界砖块", zh_tw = "浮雕紅色地獄磚塊")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_RED_NETHER_BRICKS = register("chiseled_red_nether_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.NETHER));

    // 玄武岩系列
    @I18n(en_us = "Basalt Stairs", zh_cn = "玄武岩楼梯", zh_tw = "玄武岩樓梯")
    @Stairs(type = "stone", bottom = "minecraft:block/basalt_side", top = "minecraft:block/basalt_top", side = "minecraft:block/basalt_side")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> BASALT_STAIRS = register("basalt_stairs", props -> new StairBlock(Blocks.BASALT.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Slab", zh_cn = "玄武岩台阶", zh_tw = "玄武岩臺階")
    @Slab(type = "stone", bottom = "minecraft:block/basalt_side", side = "minecraft:block/basalt_side", top = "minecraft:block/basalt_top")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> BASALT_SLAB = register("basalt_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Wall", zh_cn = "玄武岩墙", zh_tw = "玄武岩墻")
    @Wall(wall = "minecraft:block/basalt_side")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> BASALT_WALL = register("basalt_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Smooth Basalt Stairs", zh_cn = "平滑玄武岩楼梯", zh_tw = "平滑玄武岩樓梯")
    @Stairs(type = "stone", bottom = "minecraft:block/smooth_basalt", top = "minecraft:block/smooth_basalt", side = "minecraft:block/smooth_basalt")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> SMOOTH_BASALT_STAIRS = register("smooth_basalt_stairs", props -> new StairBlock(Blocks.SMOOTH_BASALT.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Smooth Basalt Slab", zh_cn = "平滑玄武岩台阶", zh_tw = "平滑玄武岩臺階")
    @Slab(type = "stone", bottom = "minecraft:block/smooth_basalt", side = "minecraft:block/smooth_basalt", top = "minecraft:block/smooth_basalt")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> SMOOTH_BASALT_SLAB = register("smooth_basalt_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Smooth Basalt Wall", zh_cn = "平滑玄武岩墙", zh_tw = "平滑玄武岩墻")
    @Wall(wall = "minecraft:block/smooth_basalt")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> SMOOTH_BASALT_WALL = register("smooth_basalt_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Polished Basalt Stairs", zh_cn = "磨制玄武岩楼梯", zh_tw = "拋光玄武岩樓梯")
    @Stairs(type = "stone", bottom = "minecraft:block/polished_basalt_top", top = "minecraft:block/polished_basalt_top", side = "minecraft:block/polished_basalt_side")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_BASALT_STAIRS = register("polished_basalt_stairs", props -> new StairBlock(Blocks.POLISHED_BASALT.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Polished Basalt Slab", zh_cn = "磨制玄武岩台阶", zh_tw = "拋光玄武岩臺階")
    @Slab(type = "stone", bottom = "minecraft:block/polished_basalt_top", side = "minecraft:block/polished_basalt_side", top = "minecraft:block/polished_basalt_top")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_BASALT_SLAB = register("polished_basalt_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Polished Basalt Wall", zh_cn = "磨制玄武岩墙", zh_tw = "拋光玄武岩墻")
    @Wall(wall = "minecraft:block/polished_basalt_side")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_BASALT_WALL = register("polished_basalt_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_WALL).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Bricks", zh_cn = "玄武岩砖", zh_tw = "玄武岩磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> BASALT_BRICKS = register("basalt_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Cracked Basalt Bricks", zh_cn = "裂纹玄武岩砖", zh_tw = "裂紋玄武岩磚")
    @BasicBlock
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CRACKED_BASALT_BRICKS = register("cracked_basalt_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Brick Stairs", zh_cn = "玄武岩砖楼梯", zh_tw = "玄武岩磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/basalt_bricks", top = "wild_wind:block/basalt_bricks", side = "wild_wind:block/basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> BASALT_BRICK_STAIRS = register("basalt_brick_stairs", props -> new StairBlock(BASALT_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Brick Slab", zh_cn = "玄武岩砖台阶", zh_tw = "玄武岩磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/basalt_bricks", side = "wild_wind:block/basalt_bricks", top = "wild_wind:block/basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> BASALT_BRICK_SLAB = register("basalt_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Basalt Brick Wall", zh_cn = "玄武岩砖墙", zh_tw = "玄武岩磚墻")
    @Wall(wall = "wild_wind:block/basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> BASALT_BRICK_WALL = register("basalt_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Chiseled Basalt Bricks", zh_cn = "雕纹玄武岩砖", zh_tw = "浮雕玄武岩磚")
    @CubeColumn(side = "wild_wind:block/chiseled_basalt_bricks", end = "wild_wind:block/chiseled_basalt_bricks_top")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> CHISELED_BASALT_BRICKS = register("chiseled_basalt_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Basalt Bricks", zh_cn = "苔玄武岩砖", zh_tw = "青苔玄武岩磚")
    @AllBrick
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> MOSSY_BASALT_BRICKS = register("mossy_basalt_bricks", BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Basalt Brick Stairs", zh_cn = "苔玄武岩砖楼梯", zh_tw = "青苔玄武岩磚樓梯")
    @Stairs(type = "stone", bottom = "wild_wind:block/mossy_basalt_bricks", top = "wild_wind:block/mossy_basalt_bricks", side = "wild_wind:block/mossy_basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> MOSSY_BASALT_BRICK_STAIRS = register("mossy_basalt_brick_stairs", props -> new StairBlock(MOSSY_BASALT_BRICKS.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_STAIRS).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Basalt Brick Slab", zh_cn = "苔玄武岩砖台阶", zh_tw = "青苔玄武岩磚臺階")
    @Slab(type = "bricks", bottom = "wild_wind:block/mossy_basalt_bricks", side = "wild_wind:block/mossy_basalt_bricks", top = "wild_wind:block/mossy_basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> MOSSY_BASALT_BRICK_SLAB = register("mossy_basalt_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB).mapColor(MapColor.COLOR_BLACK));
    @I18n(en_us = "Mossy Basalt Brick Wall", zh_cn = "苔玄武岩砖墙", zh_tw = "青苔玄武岩磚墻")
    @Wall(wall = "wild_wind:block/mossy_basalt_bricks")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> MOSSY_BASALT_BRICK_WALL = register("mossy_basalt_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_WALL).mapColor(MapColor.COLOR_BLACK));

    //  蓝冰系列
    @I18n(en_us = "Polished Blue Ice", zh_cn = "磨制蓝冰", zh_tw = "抛光藍冰")
    @BasicBlock
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<Block> POLISHED_BLUE_ICE = register("polished_blue_ice", BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE));
    @I18n(en_us = "Polished Blue Ice Wall", zh_cn = "磨制蓝冰墙", zh_tw = "抛光藍冰墻")
    @Wall(wall = "wild_wind:block/polished_blue_ice")
    @VanillaTag(names = {"mineable/pickaxe", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> POLISHED_BLUE_ICE_WALL = register("polished_blue_ice_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE));
    @I18n(en_us = "Polished Blue Ice Stairs", zh_cn = "磨制蓝冰楼梯", zh_tw = "抛光藍冰樓梯")
    @Stairs(all = "wild_wind:block/polished_blue_ice")
    @VanillaTag(names = {"mineable/pickaxe", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> POLISHED_BLUE_ICE_STAIRS = register("polished_blue_ice_stairs", props -> new StairBlock(POLISHED_BLUE_ICE.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE));
    @I18n(en_us = "Polished Blue Ice Slab", zh_cn = "磨制蓝冰台阶", zh_tw = "抛光藍冰臺階")
    @Slab(type = "stone", all = "wild_wind:block/polished_blue_ice")
    @VanillaTag(names = {"mineable/pickaxe", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> POLISHED_BLUE_ICE_SLAB = register("polished_blue_ice_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE));

    //压实雪系列
    @I18n(en_us = "Packed Snow", zh_cn = "压实雪块", zh_tw = "壓實雪塊")//datagen -> lang 这里的数据代表数据生成后语言文件的对应内容
    @BasicBlock//datagen -> blockstate and model
    @VanillaTag(names = {"mineable/shovel", "walls"}, type = TagType.Block)//datagen -> tag
    @RegistryBlockItem//自动注册对应的物品
    //这里是方块注册的代码
    //public static final DeferredBlock<Block> 全大写下划线的代码名字 = register(游戏内方块id, 方块属性(不是方块状态))
    //修改代码名字使用shift + f6 可以快捷修改有关的所有引用 这样就不会产生别的报错
    //datagen有关的，对于方块名字的使用直接调用游戏内id 不用额外的处理配置
    public static final DeferredBlock<Block> PACKED_SNOW_BLOCK = register("packed_snow_block", BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
    @I18n(en_us = "Packed Snow Wall", zh_cn = "压实雪墙", zh_tw = "壓實雪墻")
    @Wall(wall = "wild_wind:block/packed_snow_block")
    @VanillaTag(names = {"mineable/shovel", "walls"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<WallBlock> PACKED_SNOW_WALL = register("packed_snow_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
    @I18n(en_us = "Packed Snow Stairs", zh_cn = "压实雪楼梯", zh_tw = "壓實雪樓梯")
    @Stairs(all = "wild_wind:block/packed_snow_block")
    @VanillaTag(names = {"mineable/shovel", "stairs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<StairBlock> PACKED_SNOW_STAIRS = register("packed_snow_stairs", props -> new StairBlock(PACKED_SNOW_BLOCK.get().defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));
    @I18n(en_us = "Packed Snow Slab", zh_cn = "压实雪台阶", zh_tw = "壓實雪臺階")
    @Slab(type = "stone", all = "wild_wind:block/packed_snow_block")
    @VanillaTag(names = {"mineable/shovel", "slabs"}, type = TagType.Block)
    @RegistryBlockItem
    public static final DeferredBlock<SlabBlock> PACKED_SNOW_SLAB = register("packed_snow_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK));

    @I18n(en_us = "Soul Jack o'Lantern", zh_cn = "灵魂南瓜灯", zh_tw = "靈魂南瓜燈")
    @VanillaTag(names = {"mineable/pickaxe", "sword_efficient"}, type = TagType.Block)
    @CubeOrientable(side = "minecraft:block/pumpkin_side",
            front = "wild_wind:block/soul_jack_o_lantern",
            top = "minecraft:block/pumpkin_top")
    @RegistryBlockItem
    public static final DeferredBlock<CarvedPumpkinBlock> SOUL_JACK_O_LANTERN = register(
            "soul_jack_o_lantern",
            CarvedPumpkinBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel(blockState -> 10)
                    .isValidSpawn(Blocks::always)
                    .pushReaction(PushReaction.DESTROY)
    );

    @Cross(item = false)
    @I18n(en_us = "Sculk Cilia", zh_cn = "幽匿纤芽", zh_tw = "伏聆纖芽")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<SculkCiliaBlock> SCULK_CILIA = register(
            "sculk_cilia",
            SculkCiliaBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
    );

    @I18n(en_us = "Sculk Tendril", zh_cn = "幽匿卷须", zh_tw = "伏聆卷須")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<SculkTendrilBlock> SCULK_TENDRIL = register(
            "sculk_tendril",
            SculkTendrilBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
    );

    @Cross
    @I18n(en_us = "Sculk Artery", zh_cn = "幽匿脉管", zh_tw = "伏聆脈管")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<SculkVinesBlock> SCULK_ARTERY = register(
            "sculk_artery",
            SculkVinesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES)
    );

    @Cross(item = false)
    @I18n(en_us = "Sculk Artery Plant", zh_cn = "幽匿脉管植株", zh_tw = "伏聆脈管植株")
    public static final DeferredBlock<SculkVinesPlantBlock> SCULK_ARTERY_PLANT = register(
            "sculk_artery_plant",
            SculkVinesPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT)
    );

    @Cross(item = false)
    @I18n(en_us = "Fluffy Dandelion", zh_cn = "絮绒蒲公英", zh_tw = "絮絨蒲公英")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<FlowerBlock> FLUFFY_DANDELION = register(
            "fluffy_dandelion",
            properties -> new FluffyDandelionBlock(), BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)
    );

    @I18n(en_us = "Wither Rose Bush", zh_cn = "凋零玫瑰丛", zh_tw = "凋零玫瑰叢")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<DoublePlantBlock> WITHER_ROSE_BUSH = register(
            "wither_rose_bush",
            DoublePlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
    );

    @Cross(item = false)
    @I18n(en_us = "Short Beach Grass", zh_cn = "矮沙滩草", zh_tw = "矮沙灘草")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<SimpleGrassBlock> SHORT_BEACH_GRASS = register(
            "short_beach_grass",
            properties -> new SimpleGrassBlock(properties, (state) -> state.is(Tags.Blocks.SANDS)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
    );

    @I18n(en_us = "Tall Beach Grass", zh_cn = "高沙滩草", zh_tw = "高沙灘草")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<SimpleDoublePlantBlock> TALL_BEACH_GRASS = register(
            "tall_beach_grass",
            properties -> new SimpleDoublePlantBlock(properties, (state) -> state.is(Tags.Blocks.SANDS)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
    );

    @I18n(en_us = "Tall Dead Bush", zh_cn = "枯死的高灌木", zh_tw = "枯死的高灌木")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<SimpleDoublePlantBlock> TALL_DEAD_BUSH = register(
            "tall_dead_bush",
            properties -> new SimpleDoublePlantBlock(properties, (state) -> state.is(Tags.Blocks.SANDS) || state.is(Blocks.TERRACOTTA)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)
    );

    @Cross(item = false)
    @I18n(en_us = "Thorn", zh_cn = "荆棘丛", zh_tw = "荊棘叢")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<ThornBlock> THORN = register(
            "thorn",
            ThornBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH)
    );

    @I18n(en_us = "Large Thorn", zh_cn = "大型荆棘丛", zh_tw = "大型荊棘叢")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<LargeThornBlock> LARGE_THORN = register(
            "large_thorn",
            LargeThornBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH)
    );

    @Cross(item = false)
    @I18n(en_us = "Short Aquatic Grass", zh_cn = "矮水生草", zh_tw = "矮水生草")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<AquaticBlock> SHORT_AQUATIC_GRASS = register(
            "short_aquatic_grass",
            AquaticBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS)
    );

    @I18n(en_us = "Tall Aquatic Grass", zh_cn = "高水生草", zh_tw = "高水生草")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<AquaticDoublePlantBlock> TALL_AQUATIC_GRASS = register(
            "tall_aquatic_grass",
            AquaticDoublePlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS)
    );

    @I18n(en_us = "Pointed Icicle", zh_cn = "冰锥", zh_tw = "冰錐")
    @VanillaTag(names = {"mineable/pickaxe"}, type = TagType.Block)
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<PointedIcicleBlock> POINTED_ICICLE = register(
            "pointed_icicle",
            PointedIcicleBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.ICE)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.CHIME)
                    .noOcclusion()
                    .sound(SoundType.GLASS)
                    .randomTicks()
                    .strength(0.5F, 0.5F)
                    .friction(0.98F)
                    .dynamicShape()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor((_0, _1, _2) -> false)
    );

    @Cross(item = false)
    @I18n(en_us = "Rose", zh_cn = "玫瑰", zh_tw = "玫瑰")
    @BasicBlockLocatedItem
    @RegistryBlockItem
    public static final DeferredBlock<FlowerBlock> ROSE = register(
            "rose",
            properties -> new FlowerBlock(MobEffects.NIGHT_VISION,
                    5.0F, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
    );

    @VanillaTag(names = {"frog_prefer_jump_to"}, type = TagType.Block)
    public static final DeferredBlock<LotusBlock> LOTUS = register(
            "lotus",
            LotusBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD)
    );

    @I18n(en_us = "Remains", zh_cn = "遗骸", zh_tw = "遺骸")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<RemainsBlock> REMAINS = register("remains", RemainsBlock::new,
            BlockBehaviour.Properties.of().strength(0.5f).noOcclusion().isValidSpawn((state, level, pos, type) -> false));


    @I18n(en_us = "Brazier", zh_cn = "火盆", zh_tw = "火盆")
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<BrazierBlock> BRAZIER = register("brazier",
            properties -> new BrazierBlock(true, 1, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE));

    @I18n(en_us = "Soul Brazier", zh_cn = "灵魂火盆", zh_tw = "靈魂火盆")
    @VanillaTag(names = "mineable/pickaxe", type = TagType.Block)
    @BasicItem
    @RegistryBlockItem
    public static final DeferredBlock<BrazierBlock> SOUL_BRAZIER = register("soul_brazier",
            properties -> new BrazierBlock(false, 2, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_CAMPFIRE));


    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>
    entity(String name,
           Type<?> type,
           BlockEntityType.BlockEntitySupplier<T> factory,
           DeferredBlock<?>... blocks) {

        return TILES.register(name, () -> BlockEntityType.Builder.of(factory, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)).build(type));
    }

    @RegistryBlockItemHandler
    public static void registerBlockItem(BiConsumer<ResourceLocation, Item> registry) {
        registry.accept(PALM_CROWN.getId(), new BlockItem(PALM_CROWN.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return 300;
            }
        });
    }
}
