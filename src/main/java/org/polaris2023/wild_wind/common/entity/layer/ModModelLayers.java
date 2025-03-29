package org.polaris2023.wild_wind.common.entity.layer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import org.polaris2023.wild_wind.util.Helpers;

public class ModModelLayers {

    public static final ModelLayerLocation BANNER = register("banner");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String layer) {
        return new ModelLayerLocation(Helpers.location(name), layer);
    }

}