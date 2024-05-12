package com.thevoidblock.syncac.util;

import com.thevoidblock.syncac.settings.AutoClickerSetting;
import com.thevoidblock.syncac.settings.AutoClickers;
import com.thevoidblock.syncac.settings.ModSettings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static com.thevoidblock.syncac.util.GetCarpetLoggerInfo.getTPS;

public class AutoClicker {

    public static void registerAutoClickers() {
        AutoClicker.registerAutoClicker(AutoClickers.attackAutoClicker);
        AutoClicker.registerAutoClicker(AutoClickers.useAutoClicker);
    }

    public static void registerAutoClicker(AutoClickerSetting setting) {

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    if (setting.active && ModSettings.isEnabled) {

                        setting.syncCooldown = (int) Math.max(setting.cooldown*(20/getTPS()), setting.syncCooldown);

                        if (setting.timeElapsed >= setting.syncCooldown) {

                            setting.action.run();

                            setting.timeElapsed = 0;
                            setting.syncCooldown = setting.cooldown;

                        } else {
                            setting.timeElapsed++;
                        }
                    }
                }
        );

    }
}
