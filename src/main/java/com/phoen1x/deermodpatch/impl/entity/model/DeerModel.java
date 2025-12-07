package com.phoen1x.deermodpatch.impl.entity.model;

import com.phoen1x.deermodpatch.impl.entity.anim.DeerAnimations;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.EntityValueExtraction;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.animation.Animation;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.*;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.util.math.MathHelper;

public class DeerModel extends EntityModel<DeerEntity> {
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart antlers;
    private final ModelPart body;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart saddle;
    private final Animation eatGrassAnimation;

    public DeerModel(ModelPart root) {
        super(root);
        this.neck = root.getChild("neck");
        this.head = this.neck.getChild("head");
        this.antlers = this.head.getChild("antlers");
        this.body = root.getChild("body");
        this.rightFrontLeg = this.body.getChild("right_front_leg");
        this.leftFrontLeg = this.body.getChild("left_front_leg");
        this.rightHindLeg = this.body.getChild("right_hind_leg");
        this.leftHindLeg = this.body.getChild("left_hind_leg");
        this.saddle = root.getChild("saddle");
        this.eatGrassAnimation = DeerAnimations.EAT_GRASS.createAnimation(root);
    }

    public static TexturedModelData createLayer() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData neck = modelPartData.addChild("neck", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 9.0F, -11.0F));
        neck.addChild("neck_r1", ModelPartBuilder.create().uv(32, 43).cuboid(-2.0F, -10.0F, -1.5F, 4.0F, 12.0F, 4.0F), ModelTransform.of(0.0F, -2.5F, -0.5F, 0.2356F, 0.0F, 0.0F));

        ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(32, 31).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F).uv(14, 47).cuboid(-2.0F, 0.0F, -6.0F, 4.0F, 3.0F, 3.0F).uv(0, 54).cuboid(-1.0F, 0.0F, -7.0F, 2.0F, 2.0F, 1.0F), ModelTransform.origin(0.0F, -13.0F, -2.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create(), ModelTransform.origin(-4.0F, -2.0F, 1.0F));
        right_ear.addChild("ear_r1", ModelPartBuilder.create().uv(6, 54).cuboid(-4.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F).uv(22, 53).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.6981F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.origin(4.0F, -2.0F, 1.0F));
        left_ear.addChild("ear_r2", ModelPartBuilder.create().uv(6, 54).mirrored().cuboid(2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F).mirrored(false).uv(22, 53).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 1.0F).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, -0.6981F));

        ModelPartData antlers = head.addChild("antlers", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 28.0F, 12.0F));
        antlers.addChild("antlers_l", ModelPartBuilder.create().uv(28, 47).mirrored().cuboid(0.0F, -2.0F, -23.0F, 1.0F, 2.0F, 1.0F).mirrored(false).uv(28, 47).mirrored().cuboid(1.0F, -4.0F, -22.0F, 1.0F, 2.0F, 1.0F).mirrored(false).uv(28, 47).mirrored().cuboid(2.0F, -5.0F, -21.0F, 1.0F, 2.0F, 1.0F).mirrored(false).uv(28, 50).mirrored().cuboid(2.0F, -7.0F, -22.0F, 1.0F, 1.0F, 1.0F).mirrored(false).uv(28, 47).mirrored().cuboid(3.0F, -6.0F, -21.0F, 1.0F, 2.0F, 1.0F).mirrored(false).uv(28, 47).mirrored().cuboid(4.0F, -7.0F, -22.0F, 1.0F, 2.0F, 1.0F).mirrored(false).uv(28, 50).mirrored().cuboid(5.0F, -8.0F, -23.0F, 1.0F, 1.0F, 1.0F).mirrored(false).uv(28, 50).mirrored().cuboid(0.0F, -5.0F, -21.0F, 1.0F, 1.0F, 1.0F).mirrored(false), ModelTransform.origin(1.0F, -31.0F, 11.0F));
        antlers.addChild("antlers_r", ModelPartBuilder.create().uv(28, 47).cuboid(-2.0F, -33.0F, -12.0F, 1.0F, 2.0F, 1.0F).uv(28, 47).cuboid(-3.0F, -35.0F, -11.0F, 1.0F, 2.0F, 1.0F).uv(28, 47).cuboid(-4.0F, -36.0F, -10.0F, 1.0F, 2.0F, 1.0F).uv(28, 50).cuboid(-4.0F, -38.0F, -11.0F, 1.0F, 1.0F, 1.0F).uv(28, 47).cuboid(-5.0F, -37.0F, -10.0F, 1.0F, 2.0F, 1.0F).uv(28, 47).cuboid(-6.0F, -38.0F, -11.0F, 1.0F, 2.0F, 1.0F).uv(28, 50).cuboid(-7.0F, -39.0F, -12.0F, 1.0F, 1.0F, 1.0F).uv(28, 50).cuboid(-2.0F, -36.0F, -11.0F, 1.0F, 1.0F, 1.0F), ModelTransform.NONE);

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, -21.0F, 8.0F, 4.0F, 4.0F, 3.0F).uv(14, 53).cuboid(-1.0F, -23.0F, 10.0F, 2.0F, 4.0F, 2.0F).uv(0, 0).cuboid(-4.0F, -19.0F, -13.0F, 8.0F, 9.0F, 22.0F), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        body.addChild("right_front_leg", ModelPartBuilder.create().uv(48, 43).cuboid(-0.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F), ModelTransform.origin(-3.5F, -10.0F, -10.0F));
        body.addChild("left_front_leg", ModelPartBuilder.create().uv(48, 43).mirrored().cuboid(-1.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F).mirrored(false), ModelTransform.origin(3.5F, -10.0F, -10.0F));
        body.addChild("right_hind_leg", ModelPartBuilder.create().uv(48, 43).cuboid(-0.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F), ModelTransform.origin(-3.5F, -10.0F, 8.0F));
        body.addChild("left_hind_leg", ModelPartBuilder.create().uv(48, 43).mirrored().cuboid(-1.5F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F).mirrored(false), ModelTransform.origin(3.5F, -10.0F, 8.0F));

        modelPartData.addChild("saddle", ModelPartBuilder.create().uv(0, 31).cuboid(-4.0F, -5.0F, -5.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.22F)), ModelTransform.origin(0.0F, 10.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(DeerEntity state) {
        super.setAngles(state);
        float scale = state.isBaby() ? 0.5F : 1.0F;
        float neckY = state.isBaby() ? 16.0F : 9.0F;
        float neckZ = state.isBaby() ? -6.0F : -11.0F;
        this.neck.setOrigin(0.0F, neckY, neckZ);
        this.neck.xScale = scale;
        this.neck.yScale = scale;
        this.neck.zScale = scale;

        this.body.xScale = scale;
        this.body.yScale = scale;
        this.body.zScale = scale;

        this.saddle.xScale = scale;
        this.saddle.yScale = scale;
        this.saddle.zScale = scale;

        this.saddle.visible = state.hasSaddleEquipped();

        this.head.pitch = state.getPitch() * ((float) Math.PI / 180F);
        this.head.yaw = EntityValueExtraction.getRelativeHeadYaw(state) * ((float) Math.PI / 180F);

        float limbSwing = state.limbAnimator.getAnimationProgress(1.0F);
        float limbSwingAmount = state.limbAnimator.getSpeed();
        limbSwingAmount = Math.min(limbSwingAmount, 1.0F);

        if (state.isBaby()) {
            limbSwing *= 3.0F;
        }

        this.rightHindLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftHindLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.rightFrontLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leftFrontLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.antlers.visible = !state.isSheared() && !state.isBaby();

        this.eatGrassAnimation.apply(state.eatGrassAnimationState, state.age);
    }
}