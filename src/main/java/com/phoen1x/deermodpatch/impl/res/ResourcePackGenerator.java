package com.phoen1x.deermodpatch.impl.res;

import com.phoen1x.deermodpatch.impl.entity.model.EntityModels;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.extras.api.format.atlas.AtlasAsset;

public class ResourcePackGenerator {

    public static void register() {
        PolymerResourcePackUtils.RESOURCE_PACK_AFTER_INITIAL_CREATION_EVENT.register(builder -> {
            var atlas = AtlasAsset.builder();
            for (var model : EntityModels.ALL) {
                model.generateAssets(builder::addData, atlas);
            }
            builder.addData("assets/minecraft/atlases/blocks.json", atlas.build());
        });
    }
}