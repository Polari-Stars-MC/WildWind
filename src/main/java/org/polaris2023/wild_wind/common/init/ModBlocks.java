package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;

import org.polaris2023.annotation.modelgen.item.BasicBlockItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.block.BrittleIceBlock;

import org.polaris2023.wild_wind.common.block.CookingPotBlock;
import org.polaris2023.wild_wind.common.block.GlowMucusBlock;
import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;

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

    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    public static final DeferredBlock<BrittleIceBlock> BRITTLE_ICE =
            register("brittle_ice", BrittleIceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> BRITTLE_ICE_ITEM =
            register("brittle_ice", BRITTLE_ICE);

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
    public static final DeferredBlock<Block> AZALEA_PLANKS =
            register("azalea_planks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
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

    @BasicBlockItem
    public static final DeferredItem<BlockItem> AZALEA_LOG_ITEM =
            register("azalea_log", AZALEA_LOG);
    @BasicBlockItem
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_LOG_ITEM =
            register("stripped_azalea_log", STRIPPED_AZALEA_LOG);
    @BasicBlockItem
    public static final DeferredItem<BlockItem> AZALEA_WOOD_ITEM =
            register("azalea_wood", AZALEA_WOOD);
    @BasicBlockItem
    public static final DeferredItem<BlockItem> STRIPPED_AZALEA_WOOD_ITEM =
            register("stripped_azalea_wood", STRIPPED_AZALEA_WOOD);
    @BasicBlockItem
    public static final DeferredItem<BlockItem> AZALEA_PLANKS_ITEM =
            register("azalea_planks", AZALEA_PLANKS);
    @BasicBlockItem
    public static final DeferredItem<SignItem> AZALEA_SIGN_ITEM =
            registerSign("azalea_planks", AZALEA_SIGN, AZALEA_WALL_SIGN);
    @BasicBlockItem
    public static final DeferredItem<HangingSignItem> AZALEA_HANGING_SIGN_ITEM =
            registerHangingSign("azalea_planks", AZALEA_HANGING_SIGN, AZALEA_WALL_HANGING_SIGN);

    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>
    entity(String name,
             Type<?> type,
             BlockEntityType.BlockEntitySupplier<T> factory,
             DeferredBlock<?>... blocks) {
        return TILES.register(name, () -> BlockEntityType.Builder.of(factory, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)).build(type));
    }
}
