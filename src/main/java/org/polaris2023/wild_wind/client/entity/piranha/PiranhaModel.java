package org.polaris2023.wild_wind.client.entity.piranha;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ColorableHierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.polaris2023.wild_wind.common.entity.Piranha;
import org.polaris2023.wild_wind.util.Helpers;

public class PiranhaModel extends ColorableHierarchicalModel<Piranha> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = Helpers.location("piranha", "main");
	private final ModelPart bone4;
	private final ModelPart bone;
	private final ModelPart bone3;
	private final ModelPart bone2;

	public PiranhaModel(ModelPart root) {
		this.bone4 = root.getChild("bone4");
		this.bone = this.bone4.getChild("bone");
		this.bone3 = this.bone.getChild("bone3");
		this.bone2 = this.bone4.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, -6.0F));

		PartDefinition bone = bone4.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(21, 4).addBox(0.0F, -7.0F, -2.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 12).addBox(-1.0F, -4.5F, -5.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(6, 10).addBox(-1.5F, -5.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 5.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, -2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, -2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition bone3 = bone.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(6, 15).addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition bone2 = bone4.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(12, 0).addBox(-1.5F, -5.0F, 0.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(6, 0).addBox(0.0F, -5.0F, 3.0F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Piranha piranha, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(piranha.swim, PiranhaAnimation.SWIM, ageInTicks, 1.0F);
		animate(piranha.struggle, PiranhaAnimation.STRUGGLE, ageInTicks, 1.0F);
		animate(piranha.attack, PiranhaAnimation.BITE, ageInTicks, 1.0F);
		animate(piranha.attack2, PiranhaAnimation.BITE2, ageInTicks, 1.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgba) {
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgba);
	}

	@Override
	public ModelPart root() {
		return bone4;
	}
}