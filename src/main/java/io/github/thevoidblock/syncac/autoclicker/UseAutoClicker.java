package io.github.thevoidblock.syncac.autoclicker;

import io.github.thevoidblock.syncac.Syncac;
import io.github.thevoidblock.syncac.mixin.MinecraftClientInvoker;

import static io.github.thevoidblock.syncac.Syncac.CLIENT;

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
