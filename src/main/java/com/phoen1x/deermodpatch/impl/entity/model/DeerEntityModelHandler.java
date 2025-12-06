package com.phoen1x.deermodpatch.impl.entity.model;

import eu.pb4.factorytools.api.virtualentity.emuvanilla.PolyModelInstance;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.EntityModel;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.poly.SimpleEntityModel;
import eu.pb4.polymer.virtualentity.api.elements.InteractionElement;
import eu.pb4.polymer.virtualentity.api.elements.VirtualElement;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.poly.LeadAttachmentElement;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.poly.RideAttachmentElement;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.util.math.Vec3d;

public class DeerEntityModelHandler extends SimpleEntityModel<DeerEntity> {

    public final InteractionElement interaction;
    public final RideAttachmentElement rideAttachment;
    public final LeadAttachmentElement leadAttachment;

    public DeerEntityModelHandler(DeerEntity entity) {
        super(entity, (PolyModelInstance<EntityModel<DeerEntity>>) (Object) EntityModels.DEER);

        this.rideAttachment = super.rideAttachment;
        this.leadAttachment = super.leadAttachment;

        var interactionHandler = VirtualElement.InteractionHandler.redirect(entity);
        this.interaction = new InteractionElement(interactionHandler);
        float width = entity.getWidth();
        float height = entity.getHeight();
        this.interaction.setSize(width, height);
        this.rideAttachment.setOffset(new Vec3d(0, 1.3, 0));
        this.leadAttachment.setOffset(new Vec3d(0, height / 2, 0));

        this.addPassengerElement(this.interaction);
    }
}