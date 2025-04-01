package org.polaris2023.wild_wind.common.init;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wild_wind.WildWindMod;

public class ModAttachmentTypes {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, WildWindMod.MOD_ID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> MILKING_INTERVALS = registerInteger("milking_intervals");
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> SQUID_CONVERSION_TIME = registerInteger("squid_conversion_time");
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> SHOULD_SQUID_CONVERT = registerBoolean("should_squid_convert");
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> SHOULD_ITEM_FRAME_INVISIBLE = registerBoolean("should_item_frame_invisible");

    private static DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> registerInteger(String name) {
        return ATTACHMENT_TYPES.register(name, () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());
    }

    private static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> registerBoolean(String name) {
        return ATTACHMENT_TYPES.register(name, () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
    }

}