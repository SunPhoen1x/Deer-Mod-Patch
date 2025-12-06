package com.phoen1x.deermodpatch.impl.entity.anim;

import eu.pb4.factorytools.api.virtualentity.emuvanilla.animation.*;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.animation.AnimationDefinition.Builder;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.animation.Transformation.*;

public class DeerAnimations {
    public static final AnimationDefinition EAT_GRASS;

    static {
        EAT_GRASS = Builder.create(2.0F)
            .addBoneAnimation("neck", new Transformation(Targets.ROTATE, new Keyframe[]{
                new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(0.4167F, AnimationHelper.createRotationalVector(90.0F, 0.0F, 0.0F), Interpolations.CUBIC),
                new Keyframe(0.75F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(0.9167F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(1.0833F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(1.25F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(1.4167F, AnimationHelper.createRotationalVector(105.0F, 0.0F, 0.0F), Interpolations.LINEAR),
                new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Interpolations.CUBIC)
            }))
            .build();
    }
}