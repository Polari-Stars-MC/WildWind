package org.polaris2023.wild_wind.common.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.polaris2023.wild_wind.common.init.ModAttachmentTypes;
import org.polaris2023.wild_wind.util.Helpers;

public record SquidConvertPacket(int entityID) implements CustomPacketPayload {

    public static final Type<SquidConvertPacket> TYPE = new Type<>(Helpers.location("squid_convert"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SquidConvertPacket> STREAM_CODEC =
            CustomPacketPayload.codec(SquidConvertPacket::write, SquidConvertPacket::new);

    public SquidConvertPacket(FriendlyByteBuf buf) {
        this(buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(this.entityID);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SquidConvertPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Entity entity = context.player().level().getEntity(packet.entityID);
            if (entity != null) {
                entity.setData(ModAttachmentTypes.SHOULD_SQUID_CONVERT, true);
            }
        });
    }

}