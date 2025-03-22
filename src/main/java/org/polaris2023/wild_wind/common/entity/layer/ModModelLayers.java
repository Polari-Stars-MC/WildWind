package org.polaris2023.wild_wind.common.entity.layer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import org.polaris2023.wild_wind.util.Helpers;

public class ModModelLayers {
    public static final ModelLayerLocation BANNER = register("banner");

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(Helpers.location(p_171301_), p_171302_);
    }
}
