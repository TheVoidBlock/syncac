package com.thevoidblock.syncac;

import com.thevoidblock.syncac.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Syncac implements ModInitializer {
    public static final String MOD_ID = "syncac";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        LOGGER.info(AutoConfig.getConfigHolder(ModConfig.class).getConfig().toString());

        LOGGER.info(String.format("clickity clack clack (%s initialized)", MOD_ID));
    }
}
