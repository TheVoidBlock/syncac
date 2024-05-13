package com.thevoidblock.syncac.gui;

import com.thevoidblock.syncac.Syncac;
import com.thevoidblock.syncac.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable(String.format("autoclicker.%s.config", Syncac.MOD_ID)));

        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(ModConfig.class).save());

        builder.setShouldListSmoothScroll(true);

        ConfigCategory generalCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.general", Syncac.MOD_ID))
        );
        ConfigCategory attackCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.attack", Syncac.MOD_ID))
        );
        ConfigCategory useCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.use", Syncac.MOD_ID))
        );

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //General Auto Clicker Config
        generalCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.general_active", Syncac.MOD_ID)
                                ),
                                AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED
                        )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED = newValue)
                        .build()
        );

        //Attack Auto Clicker Config
        attackCategory.addEntry(
                entryBuilder.startBooleanToggle(
                        Text.translatable(
                                String.format("option.%s.attack_active", Syncac.MOD_ID)
                        ),
                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED
                )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED = newValue)
                        .build()
        );

        attackCategory.addEntry(
                entryBuilder.startIntField(
                        Text.translatable(
                                String.format("option.%s.attack_interval", Syncac.MOD_ID)
                        ), AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_INTERVAL
                )
                    .setDefaultValue(20)
                    .setTooltip(Text.translatable(String.format("tooltip.%s.attack_interval", Syncac.MOD_ID)))
                    .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_INTERVAL = newValue)
                    .build()
        );

        attackCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.attack_sync", Syncac.MOD_ID)
                                ),
                                AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_SYNC_ENABLED
                        )
                        .setDefaultValue(true)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.attack_sync", Syncac.MOD_ID)))
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_SYNC_ENABLED = newValue)
                        .build()
        );

        //Use Auto Clicker Config
        useCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.use_active", Syncac.MOD_ID)
                                ),
                                AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED
                        )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED = newValue)
                        .build()
        );

        useCategory.addEntry(
                entryBuilder.startIntField(
                                Text.translatable(
                                        String.format("option.%s.use_interval", Syncac.MOD_ID)
                                ), AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_INTERVAL
                        )
                        .setDefaultValue(20)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.use_interval", Syncac.MOD_ID)))
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_INTERVAL = newValue)
                        .build()
        );

        useCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.use_sync", Syncac.MOD_ID)
                                ),
                                AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_SYNC_ENABLED
                        )
                        .setDefaultValue(true)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.use_sync", Syncac.MOD_ID)))
                        .setSaveConsumer(newValue -> AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_SYNC_ENABLED = newValue)
                        .build()
        );

        return builder.build();
    }
}
