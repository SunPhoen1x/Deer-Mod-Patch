package com.phoen1x.deermodpatch.mixin;

import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PassiveEntity.class)
public class PassiveEntityMixin {

    @Inject(method = "growUp(IZ)V", at = @At("TAIL"))
    private void polymer_manualGrowthParticles(int age, boolean overGrow, CallbackInfo ci) {
        PassiveEntity self = (PassiveEntity) (Object) this;
        if (!self.getWorld().isClient && self instanceof DeerEntity && age > 0) {
            ServerWorld serverWorld = (ServerWorld) self.getWorld();

            serverWorld.spawnParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    self.getX(), 
                    self.getRandomBodyY() + 0.5, 
                    self.getZ(),
                    7,
                    0.3,
                    0.3,
                    0.3,
                    0.0
            );
        }
    }
}