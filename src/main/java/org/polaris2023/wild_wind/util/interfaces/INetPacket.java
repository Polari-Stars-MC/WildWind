package org.polaris2023.wild_wind.util.interfaces;

import net.minecraft.network.RegistryFriendlyByteBuf;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/09 19:51:50}
 */
public interface INetPacket {
    void write(RegistryFriendlyByteBuf buf);
}
