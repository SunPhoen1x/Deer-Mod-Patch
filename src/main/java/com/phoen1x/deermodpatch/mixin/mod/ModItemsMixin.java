package com.phoen1x.deermodpatch.mixin.mod;

import eu.pb4.polymer.core.api.item.PolymerItem;
import mei.arisuwu.deermod.ModItems;
import com.phoen1x.deermodpatch.impl.item.PolyBaseItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;
import java.util.function.Supplier;

@Mixin(value = ModItems.class, remap = false)
public abstract class ModItemsMixin {
    @Inject(
        method = "registerItem(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/item/Item$Settings;)Ljava/util/function/Supplier;", 
        at = @At("RETURN"), 
        remap = false
    )
    private Supplier<Item> polymerifyItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings, CallbackInfoReturnable<Supplier<Item>> cir) {
        Item registeredItem = cir.getReturnValue().get();
        PolymerItem polymerItem = new PolyBaseItem(registeredItem);
        PolymerItem.registerOverlay(registeredItem, polymerItem);
        return cir.getReturnValue();
    }
}