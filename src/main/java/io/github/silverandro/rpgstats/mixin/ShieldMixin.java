package io.github.silverandro.rpgstats.mixin;

import io.github.silverandro.rpgstats.LevelUtils;
import io.github.silverandro.rpgstats.stats.Components;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(PlayerEntity.class)
public class ShieldMixin {
    @Inject(at = @At("HEAD"), method = "damageShield")
    private void rpgstats$onShieldUse(float amount, CallbackInfo ci) {
        //noinspection ConstantConditions
        if ((Object) this instanceof ServerPlayerEntity && new Random().nextBoolean()) {
            LevelUtils.INSTANCE.addXpAndLevelUp(Components.DEFENCE, (ServerPlayerEntity) (Object) this, (int) Math.floor(amount / 2.2));
        }
    }
}