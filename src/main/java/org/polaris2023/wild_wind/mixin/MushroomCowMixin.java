package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModEntityDataAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author : baka4n
 * {@code @Date : 2025/03/22 21:48:09}
 */
@Mixin(MushroomCow.class)
public abstract class MushroomCowMixin extends Cow {
    public MushroomCowMixin(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "mobInteract",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemUtils;createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/item/ItemStack;"
            ), cancellable = true)
    private void interact(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (entityData.get(ModEntityDataAccess.MILKING_INTERVALS_BY_COW) > 0) {
            player.sendSystemMessage(ModTranslateKey.MOOSHROOM_COW_INTOLERANCE.translatable());
            cir.setReturnValue(super.mobInteract(player, hand));
            cir.cancel();
        }
        entityData.set(ModEntityDataAccess.MILKING_INTERVALS_BY_COW, 6000);
    }
}
