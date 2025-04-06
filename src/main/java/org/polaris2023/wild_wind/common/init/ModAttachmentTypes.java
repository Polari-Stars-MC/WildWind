package org.polaris2023.wild_wind.common.init;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.annotation.register.RegistryHandler;
import org.polaris2023.annotation.register.attachments.AttachmentBoolean;
import org.polaris2023.annotation.register.attachments.AttachmentInteger;
import org.polaris2023.wild_wind.WildWindMod;
@SuppressWarnings("NotNullFieldNotInitialized")
@RegistryHandler(RegistryHandler.Type.AttachmentType)
public class ModAttachmentTypes {

    public static final DeferredRegister<AttachmentType<?>> REGISTER = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, WildWindMod.MOD_ID);
    @AttachmentInteger
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> MILKING_INTERVALS;
    @AttachmentInteger
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> SQUID_CONVERSION_TIME;
    @AttachmentBoolean
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> SHOULD_SQUID_CONVERT;

}