package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;

import static org.polaris2023.wild_wind.common.init.ModItems.ITEMS;

public class ModBlocks {
    static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildWindMod.MOD_ID);
    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar");

    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");
    public static final DeferredItem<BlockItem> GLAREFLOWER_ITEM = register("glareflower", GLAREFLOWER);

    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");
    public static final DeferredItem<BlockItem> GLAREFLOWER_SEEDS_ITEM = register("glareflower_seeds", GLAREFLOWER_SEEDS);
    @I18n(en_us = "Spider Egg", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> SPIDER_EGG = register("spider_egg");

    @I18n(en_us = "Spiderr Attachments", zh_cn = "蛛丝附层", zh_tw = "蛛絲附層")
    public static final DeferredBlock<Block> SPIDER_ATTACHMENTS = register("spider_attachments");
    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> SPIDER_MUCOSA = register("spider_mucosa");

    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    public static final DeferredBlock<Block> REEDS = register("reeds");
    public static final DeferredItem<BlockItem> REEDS_ITEM =  register("reeds", REEDS);

    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    public static final DeferredBlock<Block> CATTAILS = register("cattails");
    public static final DeferredItem<BlockItem> CATTAILS_ITEM = register("cattails", CATTAILS);

    private static DeferredItem<BlockItem> register(String name, DeferredBlock<Block> block) {
        return ITEMS.registerSimpleBlockItem(name, block);
    }

    private static DeferredBlock<Block> register(String name) {
        return BLOCKS.registerSimpleBlock(name, BlockBehaviour.Properties.of());
    }

    private static DeferredBlock<Block> register(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.registerSimpleBlock(name, properties);
    }
}
