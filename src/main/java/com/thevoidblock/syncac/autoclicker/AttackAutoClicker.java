package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.mixin.MinecraftClientInvoker;

import static com.thevoidblock.syncac.Syncac.CLIENT;
import static com.thevoidblock.syncac.Syncac.getConfig;

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
