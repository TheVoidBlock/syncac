package io.github.thevoidblock.syncac.autoclicker;

import io.github.thevoidblock.syncac.mixin.MinecraftClientInvoker;

import static io.github.thevoidblock.syncac.Syncac.CLIENT;
import static io.github.thevoidblock.syncac.Syncac.getConfig;

public class AttackAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return getConfig().attackEnabled;
    }

    @Override
    public int getInterval() {
        return getConfig().attackHold ? 0 : getConfig().attackInterval;
    }

    @Override
    public boolean isSync() {
        return getConfig().attackSync;
    }

    @Override
    public void run() {
        if(CLIENT.player != null && !CLIENT.player.isUsingItem()) {
            ((MinecraftClientInvoker) CLIENT).invokeDoAttack();
            ((MinecraftClientInvoker) CLIENT).invokeHandleBlockBreaking(getConfig().attackHold);
        }
    }
}
