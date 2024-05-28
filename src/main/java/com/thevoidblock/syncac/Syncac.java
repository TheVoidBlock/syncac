package com.thevoidblock.syncac;

import com.thevoidblock.syncac.util.AutoClicker;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Syncac implements ClientModInitializer {
    public static final String MOD_ID = "syncac";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {

        AutoConfig.register(SyncacConfig.class, JanksonConfigSerializer::new);

        AutoClicker.registerAutoClickers();
        KeyBindings.registerModKeybindings();

        LOGGER.info(String.format("%s initialized!", MOD_ID));
    }
}
