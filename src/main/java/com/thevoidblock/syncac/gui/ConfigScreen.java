package com.thevoidblock.syncac.gui;

import com.thevoidblock.syncac.SyncacConfig;
import com.thevoidblock.syncac.util.GetCarpetLoggerInfo;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.regex.Pattern;

import static com.thevoidblock.syncac.Syncac.MOD_ID;

public class ConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        
        SyncacConfig config = AutoConfig.getConfigHolder(SyncacConfig.class).getConfig();
        
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable(String.format("autoclicker.%s.autoclicker", MOD_ID)));

        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(SyncacConfig.class).save());

        builder.setShouldListSmoothScroll(true);

        ConfigCategory generalCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.general", MOD_ID))
        );
        ConfigCategory attackCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.attack", MOD_ID))
        );
        ConfigCategory useCategory = builder.getOrCreateCategory(
                Text.translatable(String.format("category.%s.use", MOD_ID))
        );

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //General Auto Clicker Config
        generalCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.general_active", MOD_ID)
                                ),
                                config.MOD_ENABLED
                        )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.MOD_ENABLED = newValue)
                        .build()
        );

        generalCategory.addEntry(
                entryBuilder.startStrField(
                                Text.translatable(
                                        String.format("option.%s.general_regex", MOD_ID)
                                ),
                                config.TPS_FINDER_REGEX
                        )
                        .setDefaultValue("TPS: (?<tps>[0-9]+[\\.,][0-9]) MSPT: (?<mspt>[0-9]+[\\.,][0-9])")
                        .setSaveConsumer(newValue -> {
                            config.TPS_FINDER_REGEX = newValue;
                            GetCarpetLoggerInfo.PATTERN_CARPET_TPS = Pattern.compile(newValue);
                        })
                        .build()
        );

        //Attack Auto Clicker Config
        attackCategory.addEntry(
                entryBuilder.startBooleanToggle(
                        Text.translatable(
                                String.format("option.%s.attack_active", MOD_ID)
                        ),
                        config.ATTACK_ENABLED
                )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.ATTACK_ENABLED = newValue)
                        .build()
        );

        attackCategory.addEntry(
                entryBuilder.startIntField(
                        Text.translatable(
                                String.format("option.%s.attack_interval", MOD_ID)
                        ), config.ATTACK_INTERVAL
                )
                    .setDefaultValue(20)
                    .setTooltip(Text.translatable(String.format("tooltip.%s.attack_interval", MOD_ID)))
                    .setSaveConsumer(newValue -> config.ATTACK_INTERVAL = newValue)
                    .build()
        );

        attackCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.attack_sync", MOD_ID)
                                ),
                                config.ATTACK_SYNC_ENABLED
                        )
                        .setDefaultValue(true)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.attack_sync", MOD_ID)))
                        .setSaveConsumer(newValue -> config.ATTACK_SYNC_ENABLED = newValue)
                        .build()
        );

        //Use Auto Clicker Config
        useCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.use_active", MOD_ID)
                                ),
                                config.USE_ENABLED
                        )
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> config.USE_ENABLED = newValue)
                        .build()
        );

        useCategory.addEntry(
                entryBuilder.startIntField(
                                Text.translatable(
                                        String.format("option.%s.use_interval", MOD_ID)
                                ), config.USE_INTERVAL
                        )
                        .setDefaultValue(20)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.use_interval", MOD_ID)))
                        .setSaveConsumer(newValue -> config.USE_INTERVAL = newValue)
                        .build()
        );

        useCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable(
                                        String.format("option.%s.use_sync", MOD_ID)
                                ),
                                config.USE_SYNC_ENABLED
                        )
                        .setDefaultValue(true)
                        .setTooltip(Text.translatable(String.format("tooltip.%s.use_sync", MOD_ID)))
                        .setSaveConsumer(newValue -> config.USE_SYNC_ENABLED = newValue)
                        .build()
        );

        return builder.build();
    }
}
