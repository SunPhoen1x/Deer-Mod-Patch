package com.phoen1x.deermodpatch.impl.entity.model;

import eu.pb4.factorytools.api.virtualentity.emuvanilla.PolyModelInstance;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.EntityModel;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.poly.SimpleEntityModel;
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
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPassengersSetS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.List;
import java.util.function.Consumer;

public record DeerPolymerEntity(LivingEntity entity) implements PolymerEntity {
    public static final Identifier MODEL = ModResourceLocation.of("deer_model");

    public DeerPolymerEntity {
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
            var modelHandler = (DeerEntityModelHandler) UniqueIdentifiableAttachment.get(entity, MODEL).holder();

            if (packet1.getPassengerIds().length != 0) {
                consumer.accept(VirtualEntityUtils.createRidePacket(entity.getId(), IntList.of(modelHandler.interaction.getEntityId())));
                consumer.accept(VirtualEntityUtils.createRidePacket(modelHandler.rideAttachment.getEntityId(), packet1.getPassengerIds()));
            } else {
                consumer.accept(VirtualEntityUtils.createRidePacket(entity.getId(), IntList.of()));
                consumer.accept(VirtualEntityUtils.createRidePacket(modelHandler.rideAttachment.getEntityId(), IntList.of()));
            }
            return;
        }

        if (packet instanceof EntityAttachS2CPacket packet1) {
            var modelHandler = (DeerEntityModelHandler) UniqueIdentifiableAttachment.get(entity, MODEL).holder();
            consumer.accept(VirtualEntityUtils.createEntityAttachPacket(modelHandler.leadAttachment.getEntityId(), packet1.getHoldingEntityId()));
            return;
        }

        if (packet instanceof EntityStatusS2CPacket packet1) {
            this.entity.handleStatus(packet1.getStatus());
        }

        PolymerEntity.super.onEntityPacketSent(consumer, packet);
    }
}