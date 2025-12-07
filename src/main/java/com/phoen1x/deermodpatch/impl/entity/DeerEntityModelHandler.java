package com.phoen1x.deermodpatch.impl.entity;

import com.phoen1x.deermodpatch.impl.entity.model.EntityModels;
import com.phoen1x.deermodpatch.mixin.LivingEntityAccessor;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.PolyModelInstance;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.EntityModel;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.poly.SimpleEntityModel;
import eu.pb4.polymer.virtualentity.api.elements.InteractionElement;
import eu.pb4.polymer.virtualentity.api.elements.VirtualElement;
import mei.arisuwu.deermod.entity.deer.DeerEntity;
import net.minecraft.util.math.Vec3d;

public class DeerEntityModelHandler extends SimpleEntityModel<DeerEntity> {
    public final InteractionElement interaction;
    public final LeadAttachmentElement leadAttachment;
    public final RideAttachmentElement rideAttachment;

    private float currentHeight = -1;

    public DeerEntityModelHandler(DeerEntity entity) {
        super(entity, (PolyModelInstance<EntityModel<DeerEntity>>) (Object) EntityModels.DEER);

        var interactionHandler = VirtualElement.InteractionHandler.redirect(entity);
        this.interaction = new InteractionElement(interactionHandler);
        this.leadAttachment = new LeadAttachmentElement();
        this.leadAttachment.setInteractionHandler(interactionHandler);
        this.rideAttachment = new RideAttachmentElement();
        this.rideAttachment.setInteractionHandler(interactionHandler);
        this.updateDimensions();
        this.addElement(this.leadAttachment);
        this.addElement(this.rideAttachment);
        this.addPassengerElement(this.interaction);
    }

    @Override
    public void onTick() {
        super.onTick();
        this.rideAttachment.setMaxHealth(this.entity.getMaxHealth());
        this.rideAttachment.getDataTracker().set(LivingEntityAccessor.getHEALTH(), this.entity.getHealth());
        if (this.entity.getHeight() != this.currentHeight) {
            this.updateDimensions();
        }
    }

    private void updateDimensions() {
        this.currentHeight = this.entity.getHeight();
        float width = this.entity.getWidth();
        float height = this.currentHeight;
        this.interaction.setSize(width, height);
        this.leadAttachment.setOffset(new Vec3d(0, height * 0.5, 0));
        this.rideAttachment.setOffset(new Vec3d(0, height * 0.6, 0));
    }
}