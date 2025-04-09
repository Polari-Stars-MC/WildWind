package org.polaris2023.wild_wind.common.network.packets;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.polaris2023.wild_wind.common.WildWindGameEventHandler;
import org.polaris2023.wild_wind.util.Helpers;
import org.polaris2023.wild_wind.util.interfaces.INetPacket;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/13 19:56:11}
 */
public record EggShootPacket() implements CustomPacketPayload, INetPacket {
    public static final Type<EggShootPacket> TYPE = new Type<>(Helpers.location("egg_shoot"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EggShootPacket> STREAM_CODEC = CustomPacketPayload.codec(
            EggShootPacket::write, EggShootPacket::new
    );

    public static void handle(EggShootPacket ignoredEs, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();

            WildWindGameEventHandler.eggShoot(mainHandItem.is(Items.EGG) ? mainHandItem : offHandItem, player, player.level());
        });
    }

    public EggShootPacket(RegistryFriendlyByteBuf buf) {
        this();
    }

    public void write(RegistryFriendlyByteBuf buf) {}

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
