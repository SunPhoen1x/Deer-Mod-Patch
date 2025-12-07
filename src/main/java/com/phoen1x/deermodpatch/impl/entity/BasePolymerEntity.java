package com.phoen1x.deermodpatch.impl.entity;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import eu.pb4.polymer.virtualentity.api.VirtualEntityUtils;
import eu.pb4.polymer.virtualentity.api.attachment.IdentifiedUniqueEntityAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.UniqueIdentifiableAttachment;
import eu.pb4.polymer.virtualentity.api.tracker.DisplayTrackedData;
import it.unimi.dsi.fastutil.ints.IntList;
import mei.arisuwu.deermod.ModResourceLocation;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;
import java.util.function.Consumer;

public record BasePolymerEntity(LivingEntity entity) implements PolymerEntity {
    public static final Identifier MODEL = ModResourceLocation.of("deer_model");

    public BasePolymerEntity {
        var handler = new DeerEntityModelHandler((DeerEntity) entity);
        IdentifiedUniqueEntityAttachment.ofTicking(MODEL, handler, entity);
    }

    @Override
    public EntityType<?> getPolymerEntityType(PacketContext packetContext) {
        return EntityType.ITEM_DISPLAY;
    }

    @Override
    public void modifyRawTrackedData(List<DataTracker.SerializedEntry<?>> data, ServerPlayerEntity player, boolean initial) {
        PolymerEntity.super.modifyRawTrackedData(data, player, initial);
        if (initial) {
            data.add(DataTracker.SerializedEntry.of(DisplayTrackedData.TELEPORTATION_DURATION, 3));
        }
    }

    @Override
    public void onEntityPacketSent(Consumer<Packet<?>> consumer, Packet<?> packet) {
        if (packet instanceof EntityAnimationS2CPacket) {
            return;
        }
        if (packet instanceof EntityPassengersSetS2CPacket packet1) {
            var model = (DeerEntityModelHandler) UniqueIdentifiableAttachment.get(entity, MODEL).holder();
            consumer.accept(VirtualEntityUtils.createRidePacket(entity.getId(), IntList.of(model.interaction.getEntityId())));
            consumer.accept(VirtualEntityUtils.createRidePacket(model.rideAttachment.getEntityId(), packet1.getPassengerIds()));
            return;
        }

        if (packet instanceof EntityAttachS2CPacket packet1) {
            var model = (DeerEntityModelHandler) UniqueIdentifiableAttachment.get(entity, MODEL).holder();
            consumer.accept(VirtualEntityUtils.createEntityAttachPacket(model.leadAttachment.getEntityId(), packet1.getHoldingEntityId()));
            return;
        }

        if (packet instanceof EntityStatusS2CPacket statusPacket) {
            if (statusPacket.getEntity(this.entity.getWorld()) == this.entity) {
                int status = statusPacket.getStatus();
                if (status == 18) {
                    this.broadcastParticle(ParticleTypes.HEART, 7, 0.3, 0.0);
                } else if (status == 19) {
                    this.broadcastParticle(ParticleTypes.SMOKE, 7, 0.3, 0.0);
                }
            }
        }
        if (packet instanceof ParticleS2CPacket particlePacket) {
            if (particlePacket.getParameters().getType() == ParticleTypes.HAPPY_VILLAGER) {
                this.broadcastParticle(ParticleTypes.HAPPY_VILLAGER, particlePacket.getCount(), 0.5, 0.0);
                return;
            }
        }

        PolymerEntity.super.onEntityPacketSent(consumer, packet);
    }

    private void broadcastParticle(ParticleEffect particle, int count, double spread, double speed) {
        ParticleS2CPacket packet = new ParticleS2CPacket(
                particle,
                false,
                false,
                this.entity.getX(),
                this.entity.getBodyY(0.5) + 0.5,
                this.entity.getZ(),
                (float) spread, (float) spread, (float) spread,
                (float) speed,
                count
        );

        if (this.entity.getWorld() instanceof ServerWorld serverWorld) {
            for (ServerPlayerEntity player : serverWorld.getPlayers()) {
                if (player.squaredDistanceTo(this.entity) < 1024) {
                    player.networkHandler.sendPacket(packet);
                }
            }
        }
    }
}