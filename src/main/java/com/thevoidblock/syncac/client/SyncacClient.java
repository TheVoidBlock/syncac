package com.thevoidblock.syncac.client;

import com.thevoidblock.syncac.AutoClicker;
import com.thevoidblock.syncac.KeyBindings;
import com.thevoidblock.syncac.Syncac;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class SyncacClient implements ClientModInitializer {
    public static MinecraftClient CLIENT = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {

        KeyBindings.registerModKeybindings();
        AutoClicker.initializeAutoClicker();

        Syncac.LOGGER.info(String.format("clickity clack clack (%s client initialized)", Syncac.MOD_ID));
    }
}
