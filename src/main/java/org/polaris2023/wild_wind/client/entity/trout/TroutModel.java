package org.polaris2023.wild_wind.client.entity.trout;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.polaris2023.wild_wind.common.entity.Trout;
import org.polaris2023.wild_wind.util.Helpers;

public class TroutModel extends ColorableHierarchicalModel<Trout> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = Helpers.location("trout", "main");
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart bone2;

	public TroutModel(ModelPart root) {

		this.root = root;
		this.bone = root.getChild("bone");
		this.bone2 = root.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(4, 10).addBox(-0.3F, -1.9657F, -1.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(15, 16).addBox(4.7F, -1.4657F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.3F, -3.9657F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0343F, 24.0F, -0.3F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.4142F, 0.4142F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 2.0343F, 0.0F, 0.7854F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.4142F, 0.4142F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 2.0343F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(12, 13).addBox(-5.0F, -2.0F, -1.5F, 5.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -1.5F, 0.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -4.0F, 0.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = bone2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.4142F, 0.4142F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.0F, 0.0F, 0.7854F, 3.1416F, 0.0F));

		PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 1.4142F, 0.4142F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Trout trout, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		animate(trout.swim, TroutAnimation.SWIM, ageInTicks, 1.0F);
		animate(trout.struggle, TroutAnimation.STRUGGLE, ageInTicks, 1.0F);
		animate(trout.attack, TroutAnimation.STRUGGLE2, ageInTicks, 1.0F);
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgba) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgba);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}