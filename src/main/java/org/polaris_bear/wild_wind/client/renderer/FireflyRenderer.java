package org.polaris_bear.wild_wind.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.tt432.eyelib.capability.RenderData;
import io.github.tt432.eyelib.capability.component.AnimationComponent;
import io.github.tt432.eyelib.client.ClientTickHandler;
import io.github.tt432.eyelib.client.animation.BrAnimator;
import io.github.tt432.eyelib.client.loader.BrModelLoader;
import io.github.tt432.eyelib.client.render.BrModelTextures;
import io.github.tt432.eyelib.client.render.ModelRenderer;
import io.github.tt432.eyelib.client.render.RenderParams;
import io.github.tt432.eyelib.client.render.visitor.BuiltInBrModelRenderVisitors;
import io.github.tt432.eyelib.client.render.visitor.ModelRenderVisitorList;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.polaris_bear.wild_wind.common.entity.Firefly;
import org.polaris_bear.wild_wind.util.Helpers;

import java.util.HashMap;
import java.util.List;

public class FireflyRenderer extends EntityRenderer<Firefly> {
    private static final ResourceLocation FIREFLY_TEXTURE = Helpers.location("textures/entity/firefly.png");
    private static final ResourceLocation FIREFLY_MODEL = Helpers.location("firefly");

    public FireflyRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Firefly firefly) {
        return FIREFLY_TEXTURE;
    }

    @Override
    public void render(Firefly firefly, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(firefly, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        RenderData<Object> component = RenderData.getComponent(firefly);// get entity component
        AnimationComponent animationComponent = component.getAnimationComponent();

        animationComponent.setup(FIREFLY_MODEL, FIREFLY_MODEL);
        var infos = BrAnimator.tickAnimation(animationComponent,
                component.getScope(), ClientTickHandler.getTick() + partialTick);
        RenderType type = RenderType.entityCutout(FIREFLY_TEXTURE);
        ModelRenderer.render(new RenderParams(
                firefly,
                poseStack.last().copy(),
                poseStack,
                type,
                bufferSource.getBuffer(type),
                packedLight,
                LivingEntityRenderer.getOverlayCoords(firefly, 0)
        ), BrModelLoader.getModel(FIREFLY_MODEL), infos,
                new BrModelTextures.TwoSideInfoMap(new HashMap<>()),
                new ModelRenderVisitorList(List.of(BuiltInBrModelRenderVisitors.BLANK.get())));
        poseStack.popPose();
    }
}
