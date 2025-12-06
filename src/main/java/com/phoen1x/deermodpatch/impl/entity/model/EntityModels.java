package com.phoen1x.deermodpatch.impl.entity.model;

import eu.pb4.factorytools.api.virtualentity.emuvanilla.PolyModelInstance;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.EntityModel;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.ModelPart;
import eu.pb4.factorytools.api.virtualentity.emuvanilla.model.TexturedModelData;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface EntityModels {
    List<PolyModelInstance<?>> ALL = new ArrayList<>();
    Map<EntityType<?>, PolyModelInstance<?>> BY_TYPE = new IdentityHashMap<>();
    PolyModelInstance<DeerModel> DEER = create(DeerModel::new, DeerModel.createLayer(), Identifier.of("deermod", "entity/deer/deer"));

//    private static void initializeModelMap() {
//        BY_TYPE.put(ModEntities.DEER.get(), DEER);
//    }

//    static void init() {
//        initializeModelMap();
//    }

    static <T extends EntityModel<?>> PolyModelInstance<T> create(Function<ModelPart, T> modelCreator, TexturedModelData data, Identifier texture) {
        var instance = PolyModelInstance.create(modelCreator, data, texture);
        ALL.add(instance);
        return instance;
    }

    static <T extends EntityModel<?>> PolyModelInstance<T> withTexture(PolyModelInstance<T> original, Identifier texture) {
        var instance = original.withTexture(texture);
        ALL.add(instance);
        return instance;
    }
}