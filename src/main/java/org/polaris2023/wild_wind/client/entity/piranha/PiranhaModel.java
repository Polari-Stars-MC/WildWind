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
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart mouth;
	private final ModelPart bone2;
	private final ModelPart tail;

	public PiranhaModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bone = this.root.getChild("bone");
		this.mouth = this.bone.getChild("mouth");
		this.bone2 = this.root.getChild("bone2");
		this.tail = this.bone2.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(13, 0).addBox(-0.75F, -3.0F, -3.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(24, 5).addBox(-0.75F, 1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 9).addBox(-0.5F, 1.0F, -2.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 9).mirror().addBox(1.0F, 1.0F, -2.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.25F, 0.0F, -1.0F));

		PartDefinition mouth = bone.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(24, 0).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 1.5F, -1.0F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(11, 16).addBox(0.0F, -5.0F, -1.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(11, 9).addBox(-1.0F, 1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition tail = bone2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Piranha piranha, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root().getAllParts().forEach(ModelPart::resetPose);
		animate(piranha.swim, PiranhaAnimation.SWIM, ageInTicks, 1.0F);
		animate(piranha.jump, PiranhaAnimation.JUMP, ageInTicks, 1.0F);
		animate(piranha.attack, PiranhaAnimation.ATTACK, ageInTicks, 1.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgba) {
		root().render(poseStack, vertexConsumer, packedLight, packedOverlay, rgba);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}