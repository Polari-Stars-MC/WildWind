package org.polaris_bear.wild_wind.test;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import org.polaris_bear.wild_wind.WildWindMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public class WildWindGameTest {
    @SubscribeEvent
    public static void registerTest(RegisterGameTestsEvent event) {
        event.register(Test1.class);
    }
}
