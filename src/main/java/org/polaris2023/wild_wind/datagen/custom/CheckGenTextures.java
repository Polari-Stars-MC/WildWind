package org.polaris2023.wild_wind.datagen.custom;

import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.wild_wind.util.interfaces.registry.ItemRegistry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class CheckGenTextures implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modid;
    private final ExistingFileHelper helper;

    public CheckGenTextures(PackOutput output,
                            String modid,
                            CompletableFuture<HolderLookup.Provider> registries,
                            ExistingFileHelper helper) {
        this.output = output;
        this.registries = registries;
        this.modid = modid;
        this.helper = helper;
    }


    @Override
    public CompletableFuture<?> run(CachedOutput cached) {
//        BufferedImage
        Path textures = output
                .getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                .resolve(modid)
                .resolve("textures");
        Path item = textures.resolve("item");
        Collection<DeferredHolder<Item, ? extends Item>> itemEntry = ItemRegistry.items();
        CompletableFuture<Void>[] futures = new CompletableFuture[itemEntry.size()];
        Font arial = new Font("Arial", Font.PLAIN, 12);
        int i = 0;
        for (DeferredHolder<Item, ? extends Item> holder : itemEntry) {
            futures[i] = CompletableFuture.runAsync(() -> {
                if (!helper.exists(ResourceLocation.fromNamespaceAndPath(modid, "item/" + holder.getId().getPath()), ModelProvider.TEXTURE)) {
                    Path itemPath = item.resolve(holder.getId().getPath() + ".png");
                    BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = image.createGraphics();
                    graphics.setFont(arial);
                    graphics.setColor(Color.RED);
                    graphics.drawString(holder.getId().getPath(), 20, 64);
                    graphics.dispose();
                    try(ByteArrayOutputStream out = new ByteArrayOutputStream();
                        HashingOutputStream hashingOutputStream = new HashingOutputStream(Hashing.sha256(), out)
                    ) {
                        ImageIO.write(image, "PNG", hashingOutputStream);
                        cached.writeIfNeeded(itemPath, out.toByteArray(), hashingOutputStream.hash());
                    } catch (IOException ignored) {

                    }
                }
            });
            i++;
        }
        return CompletableFuture.allOf(futures);
    }

    @Override
    public String getName() {
        return "Check Generate Textures by" + modid;
    }
}
