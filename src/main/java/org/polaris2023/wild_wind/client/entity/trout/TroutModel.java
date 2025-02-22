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
	private final ModelPart bone3;
	private final ModelPart bone;
	private final ModelPart bone2;

	public TroutModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bone3 = this.root.getChild("bone3");
		this.bone = this.bone3.getChild("bone");
		this.bone2 = this.bone.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(15, 27).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -5.0F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(23, 0).addBox(0.0F, -6.0F, 0.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = bone3.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 27).mirror().addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 3.0F, 2.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r2 = bone3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 27).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 3.0F, 2.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition bone = bone3.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(0.0F, -4.0F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(15, 29).mirror().addBox(0.0F, 2.0F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(17, 14).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Trout trout, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		animate(trout.swim, TroutAnimation.SWIM, ageInTicks, 1.0F);
		animate(trout.jump, TroutAnimation.JUMP, ageInTicks, 1.0F);
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