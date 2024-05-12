package com.thevoidblock.syncac;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Syncac implements ModInitializer {
    public static final String MOD_ID = "syncac";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info(String.format("clickity clack clack (%s initialized)", MOD_ID));
    }
}
