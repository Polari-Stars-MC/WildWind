package org.polaris2023.wild_wind.common.block;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlockEntityType;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.List;

public class DDBannerBlockEntity extends BlockEntity implements Nameable {
    private static final Logger LOGGER = LogUtils.getLogger();
    @Nullable
    private Component name;
    private BannerPatternLayers patterns;
    public static final int DEFAULT_COLOR = 12030298;
    public int color;

    public DDBannerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityType.BANNER.get(), pos, blockState);
    }

    @Override
    public void setChanged() {
        if (this.level != null) {
            setChanged(this.level, this.worldPosition, this.getBlockState());
        }
    }

    @Override
    public Component getName() {
        return (Component)(this.name != null ? this.name : Component.translatable("block.wild_wind.banner"));
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }



    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.patterns.equals(BannerPatternLayers.EMPTY)) {
            tag.put("patterns", (Tag)BannerPatternLayers.CODEC.encodeStart(registries.createSerializationContext(NbtOps.INSTANCE), this.patterns).getOrThrow());
        }

        if (this.name != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.name, registries));
        }
        if (color != DEFAULT_COLOR) {
            tag.putInt("color", color);
        }

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("CustomName", 8)) {
            this.name = parseCustomNameSafe(tag.getString("CustomName"), registries);
        }

        if (tag.contains("patterns")) {
            BannerPatternLayers.CODEC.parse(registries.createSerializationContext(NbtOps.INSTANCE), tag.get("patterns")).resultOrPartial((p_331289_) -> LOGGER.error("Failed to parse banner patterns: '{}'", p_331289_)).ifPresent((p_332632_) -> this.patterns = p_332632_);
        }
        if (tag.getInt("color") == 0) {
            this.color = DEFAULT_COLOR;
        } else {
            this.color = tag.getInt("color");
        }

    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    public BannerPatternLayers getPatterns() {
        return this.patterns;
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.patterns = (BannerPatternLayers)componentInput.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        this.name = (Component)componentInput.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.BANNER_PATTERNS, this.patterns);
        components.set(DataComponents.CUSTOM_NAME, this.name);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("patterns");
        tag.remove("CustomName");
        tag.remove("color");
    }
}
