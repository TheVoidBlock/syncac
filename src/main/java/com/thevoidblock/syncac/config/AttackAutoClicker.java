package com.thevoidblock.syncac.config;

import com.thevoidblock.syncac.client.SyncacClient;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Hand;

public class AttackAutoClicker extends AutoClickerConfig {

    @Override
    public boolean isEnabled() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED;
    }

    @Override
    public int getInterval() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_INTERVAL;
    }

    @Override
    public boolean isSync() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_SYNC_ENABLED;
    }

    @Override
    public void run() {
        if(SyncacClient.CLIENT.player != null) {
            if (SyncacClient.CLIENT.targetedEntity != null) {
                assert SyncacClient.CLIENT.interactionManager != null;
                SyncacClient.CLIENT.interactionManager.attackEntity(SyncacClient.CLIENT.player, SyncacClient.CLIENT.targetedEntity);
            }
            SyncacClient.CLIENT.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
