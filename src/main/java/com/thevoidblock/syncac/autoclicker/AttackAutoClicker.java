package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.Syncac;
import net.minecraft.util.Hand;

import static com.thevoidblock.syncac.Syncac.CLIENT;

public class AttackAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return Syncac.getConfig().attackEnabled;
    }

    @Override
    public int getInterval() {
        return Syncac.getConfig().attackInterval;
    }

    @Override
    public boolean isSync() {
        return Syncac.getConfig().attackSync;
    }

    @Override
    public void run() {
        if(CLIENT.player != null) {
            if (CLIENT.targetedEntity != null) {
                assert CLIENT.interactionManager != null;
                CLIENT.interactionManager.attackEntity(CLIENT.player, CLIENT.targetedEntity);
            }
            CLIENT.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
