package com.thevoidblock.syncac.mixin;

import com.thevoidblock.syncac.SyncacConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Redirect(
            method = "handleInputEvents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z",
                    ordinal = 2
            )
    )
    private boolean overrideIfCondition(KeyBinding useKey) {
        SyncacConfig config = AutoConfig.getConfigHolder(SyncacConfig.class).getConfig();
        if(config.MOD_ENABLED && config.USE_ENABLED && config.USE_HOLD_ENABLED) return true;
        return useKey.isPressed();
    }
}
