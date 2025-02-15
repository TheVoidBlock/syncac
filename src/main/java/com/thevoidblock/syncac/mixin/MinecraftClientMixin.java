package com.thevoidblock.syncac.mixin;

import com.thevoidblock.syncac.Syncac;
import com.thevoidblock.syncac.SyncacConfig;
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
        SyncacConfig config = Syncac.getConfig();
        if(config.modEnabled && config.useEnabled && config.useHold) return true;
        return useKey.isPressed();
    }
}
