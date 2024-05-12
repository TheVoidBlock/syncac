package com.thevoidblock.syncac.mixin;

import com.thevoidblock.syncac.imixin.IMinecraftClientMixin;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements IMinecraftClientMixin {
    @Invoker
    public abstract void invokeDoItemUse();
}
