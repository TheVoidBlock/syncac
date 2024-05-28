package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.SyncacConfig;
import com.thevoidblock.syncac.Syncac;
import com.thevoidblock.syncac.mixin.MinecraftClientInvoker;
import me.shedaniel.autoconfig.AutoConfig;

public class UseAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().USE_ENABLED;
    }

    @Override
    public int getInterval() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().USE_INTERVAL;
    }

    @Override
    public boolean isSync() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().USE_SYNC_ENABLED;
    }

    @Override
    public void run() {
        if (Syncac.CLIENT.player != null)
            ((MinecraftClientInvoker) Syncac.CLIENT).invokeDoItemUse();
    }
}
