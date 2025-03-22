package com.thevoidblock.syncac.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.thevoidblock.syncac.Syncac.getConfig;

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
    private boolean cancelStopUsingItem(KeyBinding useKey) {
        if(getConfig().modEnabled && getConfig().useEnabled && getConfig().useHold) return true;
        return useKey.isPressed();
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;",
                    ordinal = 3
            )
    )
    private Screen cancelScreenCooldown(Screen original) {
        return getConfig().modEnabled && getConfig().attackEnabled ? null : original;
    }

    @Redirect(
            method = "handleInputEvents",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleBlockBreaking(Z)V")
    )
    private void cancelBlockBreaking(MinecraftClient instance, boolean breaking) {
        if(!(getConfig().modEnabled && getConfig().attackEnabled)) {
            ((MinecraftClientInvoker) instance).invokeHandleBlockBreaking(breaking);
        }
    }
}
