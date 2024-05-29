package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.SyncacConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Hand;

import static com.thevoidblock.syncac.Syncac.CLIENT;

public class AttackAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().ATTACK_ENABLED;
    }

    @Override
    public int getInterval() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().ATTACK_INTERVAL;
    }

    @Override
    public boolean isSync() {
        return AutoConfig.getConfigHolder(SyncacConfig.class).getConfig().ATTACK_SYNC_ENABLED;
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
