package io.github.thevoidblock.syncac.util;

import io.github.thevoidblock.syncac.Syncac;
import io.github.thevoidblock.syncac.SyncacConfig;
import io.github.thevoidblock.syncac.autoclicker.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static java.lang.System.nanoTime;
import static io.github.thevoidblock.syncac.util.GetCarpetLoggerInfo.getTPS;

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

                    SyncacConfig config = Syncac.getConfig();

                    if (config.modEnabled && clicker.isEnabled()) {

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
