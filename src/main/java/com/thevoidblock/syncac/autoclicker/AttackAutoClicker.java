package com.thevoidblock.syncac.autoclicker;

import com.thevoidblock.syncac.SyncacConfig;
import com.thevoidblock.syncac.Syncac;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Hand;

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
        if(Syncac.CLIENT.player != null) {
            if (Syncac.CLIENT.targetedEntity != null) {
                assert Syncac.CLIENT.interactionManager != null;
                Syncac.CLIENT.interactionManager.attackEntity(Syncac.CLIENT.player, Syncac.CLIENT.targetedEntity);
            }
            Syncac.CLIENT.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
