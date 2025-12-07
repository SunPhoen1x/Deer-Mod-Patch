package com.phoen1x.deermodpatch.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractHorseEntity.class)
public interface AbstractHorseEntityAccessor {
    @Accessor("HORSE_FLAGS")
    static TrackedData<Byte> getHORSE_FLAGS() {
        throw new UnsupportedOperationException();
    }
}