package com.thevoidblock.syncac;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Hand;

import static com.thevoidblock.syncac.util.GetCarpetLoggerInfo.getTPS;

public class AutoClicker {
    public void initializeAutoClicker() {

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    if (client.player != null) {
                        Variables.syncCooldown = (int) Math.max(Variables.cooldown*(20/getTPS()), Variables.syncCooldown);
                        if (Variables.timeLeft >= Variables.syncCooldown) {
                            if (client.targetedEntity != null)
                                client.interactionManager.attackEntity(client.player, client.targetedEntity);
                            client.player.swingHand(Hand.MAIN_HAND);

                            Variables.timeLeft = 0;
                            Variables.syncCooldown = Variables.cooldown;
                        } else {
                            Variables.timeLeft++;
                        }
                    }
                }
        );

    }
}
