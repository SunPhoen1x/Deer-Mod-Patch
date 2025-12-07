package com.phoen1x.deermodpatch.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.phoen1x.deermodpatch.impl.entity.BasePolymerEntity;
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyExpressionValue(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isClient()Z", ordinal = 1))
    private boolean serverWorldIsJustAsGoodAsClientOne(boolean original) {
       return original || PolymerEntity.get(this) instanceof BasePolymerEntity;
   }
}
