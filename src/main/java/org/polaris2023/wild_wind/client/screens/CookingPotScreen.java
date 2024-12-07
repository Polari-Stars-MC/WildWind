package org.polaris2023.wild_wind.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.util.Helpers;

public class CookingPotScreen extends Screen {
    public static final ResourceLocation GUI_TEXTURE = Helpers.location("textures/gui/cooking_pot.png");
    public CookingPotScreen() {
        super(ModTranslateKey.COOKIN_POT.translatable());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
