package com.thevoidblock.syncac.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.thevoidblock.syncac.Syncac.getConfig;

@Mixin(Mouse.class)
public class MouseMixin {
    @Redirect(
            method = "lockCursor",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/MinecraftClient;attackCooldown:I",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void cancelAttackCooldown(MinecraftClient instance, int value) {
        if(!(getConfig().modEnabled && getConfig().attackEnabled)) {
            instance.attackCooldown = value;
        }
    }
}
