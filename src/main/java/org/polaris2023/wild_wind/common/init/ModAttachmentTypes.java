package org.polaris2023.wild_wind.common.init;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.annotation.enums.RegType;
import org.polaris2023.annotation.handler.RegistryHandler;
import org.polaris2023.annotation.register.attachments.AttachmentBoolean;
import org.polaris2023.annotation.register.attachments.AttachmentInteger;
import org.polaris2023.wild_wind.WildWindMod;
@SuppressWarnings("NotNullFieldNotInitialized")
@RegistryHandler(RegType.AttachmentType)
public class ModAttachmentTypes {

    public static final DeferredRegister<AttachmentType<?>> REGISTER = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, WildWindMod.MOD_ID);
    @AttachmentInteger
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> MILKING_INTERVALS;
    @AttachmentInteger
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> SQUID_CONVERSION_TIME;
    @AttachmentBoolean
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> SHOULD_SQUID_CONVERT;
    @AttachmentBoolean
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> IS_INVISIBLE;
    @AttachmentBoolean
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> VANILLA_INVISIBLE_SAVE;
    @AttachmentBoolean
    public static DeferredHolder<AttachmentType<?>, AttachmentType<Boolean>> SPLIT_MAGMA_CUBE;

}