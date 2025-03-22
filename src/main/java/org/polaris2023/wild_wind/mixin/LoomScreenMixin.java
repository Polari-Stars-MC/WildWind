package org.polaris2023.wild_wind.mixin;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.handlers.IScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.LoomScreen;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.BannerDuplicateRecipe;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.polaris2023.wild_wind.client.renderer.ModBannerRenderer;
import org.polaris2023.wild_wind.common.item.ModBannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin extends AbstractContainerScreen<LoomMenu> {
    @Shadow
    private static final ResourceLocation BANNER_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/banner_slot");
    @Shadow
    private static final ResourceLocation DYE_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/dye_slot");
    @Shadow
    private static final ResourceLocation PATTERN_SLOT_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/pattern_slot");
    @Shadow
    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/scroller");
    @Shadow
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/scroller_disabled");
    @Shadow
    private static final ResourceLocation PATTERN_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/pattern_selected");
    @Shadow
    private static final ResourceLocation PATTERN_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/pattern_highlighted");
    @Shadow
    private static final ResourceLocation PATTERN_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/pattern");
    @Shadow
    private static final ResourceLocation ERROR_SPRITE = ResourceLocation.withDefaultNamespace("container/loom/error");
    @Shadow
    private static final ResourceLocation BG_LOCATION = ResourceLocation.withDefaultNamespace("textures/gui/container/loom.png");
    @Shadow
    private ModelPart flag;
    @Nullable
    @Shadow
    private BannerPatternLayers resultBannerPatterns;
    @Shadow
    private boolean displayPatterns;
    @Shadow
    private boolean hasMaxPatterns;
    @Shadow
    private float scrollOffs;
    @Shadow
    private int startRow;

    public LoomScreenMixin(LoomMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Inject(method = "renderBg", at = @At("HEAD"), cancellable = true)
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY, CallbackInfo ci) {
        int i = this.leftPos;
        int j = this.topPos;
        guiGraphics.blit(BG_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        Slot slot = this.menu.getBannerSlot();
        Slot slot1 = this.menu.getDyeSlot();
        Slot slot2 = this.menu.getPatternSlot();
        Slot slot3 = this.menu.getResultSlot();
        if (!slot.hasItem()) {
            guiGraphics.blitSprite(BANNER_SLOT_SPRITE, i + slot.x, j + slot.y, 16, 16);
        }

        if (!slot1.hasItem()) {
            guiGraphics.blitSprite(DYE_SLOT_SPRITE, i + slot1.x, j + slot1.y, 16, 16);
        }

        if (!slot2.hasItem()) {
            guiGraphics.blitSprite(PATTERN_SLOT_SPRITE, i + slot2.x, j + slot2.y, 16, 16);
        }

        int k = (int)(41.0F * this.scrollOffs);
        ResourceLocation resourcelocation = this.displayPatterns ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        guiGraphics.blitSprite(resourcelocation, i + 119, j + 13 + k, 12, 15);
        Lighting.setupForFlatItems();
        if (this.resultBannerPatterns != null && !this.hasMaxPatterns) {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate((float)(i + 139), (float)(j + 52), 0.0F);
            guiGraphics.pose().scale(24.0F, 24.0F, 1.0F);
            guiGraphics.pose().translate(0.5F, -0.5F, 0.5F);
            float f = 0.6666667F;
            guiGraphics.pose().scale(0.6666667F, 0.6666667F, -0.6666667F);
            this.flag.xRot = 0.0F;
            this.flag.y = -32.0F;
            if(slot3.getItem().getItem() instanceof ModBannerItem bannerItem) {
                // int color = bannerItem.getIntColor();
                ModBannerRenderer.renderPatterns(
                        guiGraphics.pose(),
                        guiGraphics.bufferSource(),
                        15728880,
                        OverlayTexture.NO_OVERLAY,
                        this.flag,
                        ModelBakery.BANNER_BASE,
                        true,
                        12030298,
                        this.resultBannerPatterns
                );
            } else {
                DyeColor dyecolor = ((BannerItem) slot3.getItem().getItem()).getColor();
                BannerRenderer.renderPatterns(
                        guiGraphics.pose(),
                        guiGraphics.bufferSource(),
                        15728880,
                        OverlayTexture.NO_OVERLAY,
                        this.flag,
                        ModelBakery.BANNER_BASE,
                        true,
                        dyecolor,
                        this.resultBannerPatterns
                );
            }
            guiGraphics.pose().popPose();
            guiGraphics.flush();
        } else if (this.hasMaxPatterns) {
            guiGraphics.blitSprite(ERROR_SPRITE, i + slot3.x - 5, j + slot3.y - 5, 26, 26);
        }

        if (this.displayPatterns) {
            int j2 = i + 60;
            int k2 = j + 13;
            List<Holder<BannerPattern>> list = this.menu.getSelectablePatterns();

            label64:
            for (int l = 0; l < 4; l++) {
                for (int i1 = 0; i1 < 4; i1++) {
                    int j1 = l + this.startRow;
                    int k1 = j1 * 4 + i1;
                    if (k1 >= list.size()) {
                        break label64;
                    }

                    int l1 = j2 + i1 * 14;
                    int i2 = k2 + l * 14;
                    boolean flag = mouseX >= l1 && mouseY >= i2 && mouseX < l1 + 14 && mouseY < i2 + 14;
                    ResourceLocation resourcelocation1;
                    if (k1 == this.menu.getSelectedBannerPatternIndex()) {
                        resourcelocation1 = PATTERN_SELECTED_SPRITE;
                    } else if (flag) {
                        resourcelocation1 = PATTERN_HIGHLIGHTED_SPRITE;
                    } else {
                        resourcelocation1 = PATTERN_SPRITE;
                    }

                    guiGraphics.blitSprite(resourcelocation1, l1, i2, 14, 14);
                    this.wild_wind$renderPattern(guiGraphics, list.get(k1), l1, i2);
                }
            }
        }

        Lighting.setupFor3DItems();
        ci.cancel();
    }

    @Unique
    private void wild_wind$renderPattern(GuiGraphics guiGraphics, Holder<BannerPattern> pattern, int x, int y) {
        PoseStack posestack = new PoseStack();
        posestack.pushPose();
        posestack.translate((float)x + 0.5F, (float)(y + 16), 0.0F);
        posestack.scale(6.0F, -6.0F, 1.0F);
        posestack.translate(0.5F, 0.5F, 0.0F);
        posestack.translate(0.5F, 0.5F, 0.5F);
        float f = 0.6666667F;
        posestack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        this.flag.xRot = 0.0F;
        this.flag.y = -32.0F;
        BannerPatternLayers bannerpatternlayers = new BannerPatternLayers.Builder().add(pattern, DyeColor.WHITE).build();
        BannerRenderer.renderPatterns(
                posestack,
                guiGraphics.bufferSource(),
                15728880,
                OverlayTexture.NO_OVERLAY,
                this.flag,
                ModelBakery.BANNER_BASE,
                true,
                DyeColor.GRAY,
                bannerpatternlayers
        );
        posestack.popPose();
        guiGraphics.flush();
    }
}
