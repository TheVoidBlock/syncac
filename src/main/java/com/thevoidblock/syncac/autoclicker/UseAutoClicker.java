package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.Syncac;
import com.thevoidblock.syncac.mixin.MinecraftClientInvoker;

import static com.thevoidblock.syncac.Syncac.CLIENT;

public class UseAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return Syncac.getConfig().useEnabled;
    }

    @Override
    public int getInterval() {
        return Syncac.getConfig().useInterval;
    }

    @Override
    public boolean isSync() {
        return Syncac.getConfig().useSync;
    }

    @Override
    public void run() {
        if (CLIENT.player != null)
            if (!CLIENT.player.isUsingItem()) {
                ((MinecraftClientInvoker) CLIENT).invokeDoItemUse();
                assert CLIENT.interactionManager != null;
                if(!Syncac.getConfig().useHold) CLIENT.interactionManager.stopUsingItem(CLIENT.player);
            }
    }
}
