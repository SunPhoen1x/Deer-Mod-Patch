package com.phoen1x.deermodpatch.mixin;

import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DeerEntity.class)
public abstract class DeerEntityMixin extends AnimalEntity {

    protected DeerEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            var passenger = this.getControllingPassenger();
            if (passenger instanceof ServerPlayerEntity player) {
                this.setYaw(player.getYaw());
                this.setPitch(player.getPitch() * 0.5F);
                this.setRotation(this.getYaw(), this.getPitch());
                this.setBodyYaw(this.getYaw());
                this.setHeadYaw(this.getYaw());
                float speed = (float) this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED);

                speed *= 1.5F;
                if (player.isSprinting()) {
                    speed *= 2.0F;
                }

                this.setMovementSpeed(speed);
                super.travel(new Vec3d(0, movementInput.y, speed));

                this.updateLimbs(false);
                return;
            }
        }

        super.travel(movementInput);
    }

    @Override
    public boolean isControlledByPlayer() {
        return false;
    }
}