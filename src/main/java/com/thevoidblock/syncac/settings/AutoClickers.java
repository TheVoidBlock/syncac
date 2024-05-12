package com.thevoidblock.syncac.settings;

import com.thevoidblock.syncac.client.SyncacClient;
import com.thevoidblock.syncac.imixin.IMinecraftClientMixin;
import net.minecraft.util.Hand;

public class AutoClickers {
    public static com.thevoidblock.syncac.settings.AutoClickerSetting attackAutoClicker = new com.thevoidblock.syncac.settings.AutoClickerSetting(
            20,
            false,
            () -> {
                if(SyncacClient.CLIENT.player != null) {
                    if (SyncacClient.CLIENT.targetedEntity != null) {
                        assert SyncacClient.CLIENT.interactionManager != null;
                        SyncacClient.CLIENT.interactionManager.attackEntity(SyncacClient.CLIENT.player, SyncacClient.CLIENT.targetedEntity);
                    }
                    SyncacClient.CLIENT.player.swingHand(Hand.MAIN_HAND);
                }
            }
    );
    public static com.thevoidblock.syncac.settings.AutoClickerSetting useAutoClicker = new com.thevoidblock.syncac.settings.AutoClickerSetting(
            20,
            false,
            () -> ((IMinecraftClientMixin)SyncacClient.CLIENT).invokeDoItemUse()
    );
}
