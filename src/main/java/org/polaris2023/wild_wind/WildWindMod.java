package org.polaris2023.wild_wind;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.polaris2023.wild_wind.common.init.*;
import org.polaris2023.wild_wind.util.interfaces.IConfig;

import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(WildWindMod.MOD_ID)
public class WildWindMod {

    public static final String MOD_ID = "wild_wind";
    public static final String MOD_VERSION = ModList.get().getModFileById(MOD_ID).versionString();

    public WildWindMod(IEventBus modEventBus, ModContainer modContainer) {
        NeoForgeMod.enableMilkFluid();
        ModInitializer.init(modEventBus);
        ModPotions.register(modEventBus);
        ModVanillaCompat.register(NeoForge.EVENT_BUS);
        ModAttachmentTypes.REGISTER.register(modEventBus);


        for (IConfig iConfig : ServiceLoader.load(IConfig.class)) {
            iConfig.register(modContainer);
        }
    }

}