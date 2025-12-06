package com.phoen1x.deermodpatch.mixin;

import com.phoen1x.deermodpatch.impl.entity.model.DeerPolymerEntity;
import com.phoen1x.deermodpatch.impl.entity.model.EntityModels; // (!) Додаємо імпорт
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import mei.arisuwu.deermod.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(value = ModEntities.class, remap = false)
public abstract class ModEntitiesMixin {
    @Inject(
            method = "registerEntityType",
            at = @At("RETURN"),
            remap = false
    )
    private <T extends Entity> void polymerifyEntityType(String name, EntityType.Builder<T> builder, CallbackInfoReturnable<java.util.function.Supplier<EntityType<T>>> cir) {

        EntityType<T> registeredEntityType = cir.getReturnValue().get();
        if (name.equals("deer")) {
            EntityModels.BY_TYPE.put(registeredEntityType, EntityModels.DEER);
            PolymerEntityUtils.registerOverlay(
                    (EntityType<LivingEntity>) registeredEntityType,
                    (Function<LivingEntity, PolymerEntity>) DeerPolymerEntity::new
            );
        }
    }
}