package com.thevoidblock.syncac.util;

import com.thevoidblock.syncac.config.*;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static com.thevoidblock.syncac.util.GetCarpetLoggerInfo.getTPS;

public class AutoClicker {

    public static void registerAutoClickers() {
        AutoClicker.registerAutoClicker(new AttackAutoClicker());
        AutoClicker.registerAutoClicker(new UseAutoClicker());
    }

    public static void registerAutoClicker(AutoClickerConfig clicker) {

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {

                    clicker.syncInterval = clicker.getInterval();

                    if (clicker.isEnabled() && AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED) {

                        if(clicker.isSync()) clicker.syncInterval = (int) Math.max(clicker.getInterval() *(20/getTPS()), clicker.syncInterval);

                        if (clicker.timeElapsed >= clicker.syncInterval) {

                            clicker.run();
                            clicker.timeElapsed = 0;

                        } else {
                            clicker.timeElapsed++;
                        }
                    }

                }
        );

    }
}
