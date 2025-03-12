package org.polaris2023.wild_wind.common.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Locale;

import static org.polaris2023.wild_wind.WildWindMod.MOD_ID;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/11 22:01:59}
 */
public enum ModPolishedSettings {
    POLISHED_STONE;
    public final BlockSetType setType;
    ModPolishedSettings() {
        String typeName = MOD_ID + "_" + name().toLowerCase(Locale.ROOT);
        setType = BlockSetType.register(new BlockSetType(typeName));

    }

    ModPolishedSettings(boolean canOpenByHand, boolean canOpenByWindCharge, boolean canButtonBeActivatedByArrows, BlockSetType.PressurePlateSensitivity pressurePlateSensitivity, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        String typeName = MOD_ID + "_" + name().toLowerCase(Locale.ROOT);
        setType = BlockSetType.register(new BlockSetType(typeName, canOpenByHand, canOpenByWindCharge, canButtonBeActivatedByArrows, pressurePlateSensitivity, soundType, doorClose, doorOpen, trapdoorClose, trapdoorOpen, pressurePlateClickOff, pressurePlateClickOn, buttonClickOff, buttonClickOn));
    }
}
