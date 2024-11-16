package org.polaris2023.wild_wind.util;

import net.minecraft.resources.ResourceLocation;

public record BRModelResourceLocation(
        ResourceLocation texture,
        ResourceLocation model,
        ResourceLocation animation,
        ResourceLocation controllers
) {}
