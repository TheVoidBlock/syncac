package com.thevoidblock.syncac.config;

import com.thevoidblock.syncac.client.SyncacClient;
import com.thevoidblock.syncac.mixin.MinecraftClientInvoker;
import me.shedaniel.autoconfig.AutoConfig;

public class UseAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED;
    }

    @Override
    public int getInterval() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_INTERVAL;
    }

    @Override
    public boolean isSync() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_SYNC_ENABLED;
    }

    @Override
    public void run() {
        if (SyncacClient.CLIENT.player != null)
            ((MinecraftClientInvoker) SyncacClient.CLIENT).invokeDoItemUse();
    }
}
