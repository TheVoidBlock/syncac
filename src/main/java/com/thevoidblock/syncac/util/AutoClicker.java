package com.thevoidblock.syncac.util;

import com.thevoidblock.syncac.SyncacConfig;
import com.thevoidblock.syncac.autoclicker.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static java.lang.System.nanoTime;
import static com.thevoidblock.syncac.util.GetCarpetLoggerInfo.getTPS;

public class AutoClicker {

    public static void registerAutoClickers() {
        AutoClicker.registerAutoClicker(new AttackAutoClicker());
        AutoClicker.registerAutoClicker(new UseAutoClicker());
    }

    private static final long TICK_TO_NANO = 50_000_000;

    public static void registerAutoClicker(AutoClickerConfig clicker) {

        clicker.startTime = nanoTime();

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {

                    SyncacConfig config = AutoConfig.getConfigHolder(SyncacConfig.class).getConfig();

                    if (config.MOD_ENABLED && clicker.isEnabled()) {

                        if(clicker.isSync())
                            if(getTPS() <= 20) clicker.syncInterval = (int) Math.max(clicker.getInterval() *(20/getTPS()), clicker.syncInterval);
                            else clicker.syncInterval = (int) Math.min(clicker.getInterval() *(20/getTPS()), clicker.syncInterval);

                        if ((nanoTime() - clicker.startTime) >= clicker.syncInterval* TICK_TO_NANO) {
                            clicker.run();
                            clicker.syncInterval = clicker.getInterval();
                            clicker.startTime = nanoTime();
                        }
                    }

                }
        );

    }
}
