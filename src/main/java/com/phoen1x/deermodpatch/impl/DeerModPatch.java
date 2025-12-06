package com.phoen1x.deermodpatch.impl;

import com.phoen1x.deermodpatch.impl.res.ResourcePackGenerator;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.extras.api.ResourcePackExtras;
import eu.pb4.polymer.resourcepack.extras.api.format.item.ItemAsset;
import eu.pb4.polymer.resourcepack.extras.api.format.item.model.BasicItemModel;
import eu.pb4.polymer.resourcepack.extras.api.format.item.tint.MapColorTintSource;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import java.util.List;

public class DeerModPatch implements ModInitializer {
    @Override
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("deermod");
        ResourcePackGenerator.register();
        ResourcePackExtras.forDefault().addBridgedModelsFolder(Identifier.of("deermod", "entity"), (id, b) -> {
            return new ItemAsset(new BasicItemModel(id, List.of(new MapColorTintSource(0xFFFFFF))), new ItemAsset.Properties(true, true));
        });
    }
}