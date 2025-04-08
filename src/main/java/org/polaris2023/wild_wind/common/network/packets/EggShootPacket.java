package org.polaris2023.wild_wind.common.network.packets;

import net.minecraft.advancements.critereon.PlayerPredicate;
import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.polaris2023.wild_wind.common.WildWindGameEventHandler;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.UUID;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/13 19:56:11}
 */
public record EggShootPacket() implements CustomPacketPayload {
    public static final Type<EggShootPacket> TYPE = new Type<>(Helpers.location("egg_shoot"));
    public static final StreamCodec<RegistryFriendlyByteBuf, EggShootPacket> STREAM_CODEC = CustomPacketPayload.codec(
            EggShootPacket::write, EggShootPacket::new
    );

    public static void handle(EggShootPacket es, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ItemStack mainHandItem = ctx.player().getMainHandItem();
            ItemStack offHandItem = ctx.player().getOffhandItem();
            WildWindGameEventHandler.eggShoot(mainHandItem.is(Items.EGG) ? mainHandItem : offHandItem, ctx.player(), ctx.player().level());
        });
    }

    public static ItemStack checkNull(RegistryFriendlyByteBuf buf) {
        try {
            return ItemStack.STREAM_CODEC.decode(buf);
        } catch (Exception e) {
            return ItemStack.EMPTY;
        }

    }

    public EggShootPacket(RegistryFriendlyByteBuf buf) {
        this();
    }

    private void write(RegistryFriendlyByteBuf buf) {}

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}
