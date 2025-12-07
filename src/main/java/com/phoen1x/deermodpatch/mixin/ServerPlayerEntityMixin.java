package com.phoen1x.deermodpatch.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.phoen1x.deermodpatch.impl.entity.BasePolymerEntity;
import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BundleS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.List;

@Mixin(value = ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Shadow public ServerPlayNetworkHandler networkHandler;

    @ModifyArg(method = "startRiding", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"))
    private Packet<?> replacePacket(Packet<?> par1, @Local(argsOnly = true) Entity entity) {
        if (PolymerEntity.get(entity) instanceof BasePolymerEntity basePolymerEntity) {
            basePolymerEntity.onEntityPacketSent(this.networkHandler::sendPacket, par1);
            return new BundleS2CPacket(List.of());
        }

        return par1;
    }

    @ModifyArg(method = "dismountVehicle", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V"))
    private Packet<?> replacePacket2(Packet<?> par1, @Local Entity entity) {
        if (PolymerEntity.get(entity) instanceof BasePolymerEntity basePolymerEntity) {
            basePolymerEntity.onEntityPacketSent(this.networkHandler::sendPacket, par1);
            return new BundleS2CPacket(List.of());
        }

        return par1;
    }
}