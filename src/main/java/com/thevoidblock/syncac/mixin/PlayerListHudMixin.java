package com.thevoidblock.syncac.mixin;

import com.thevoidblock.syncac.imixin.IPlayerListHudMixin;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin implements IPlayerListHudMixin {
    @Shadow private @Nullable Text footer;

    public Text getFooter() {
        return footer;
    }
}
