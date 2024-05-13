package com.thevoidblock.syncac.config;

import com.thevoidblock.syncac.Syncac;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = Syncac.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean MOD_ENABLED = true;

    public Boolean ATTACK_ENABLED = false;
    public Integer ATTACK_INTERVAL = 20;
    public Boolean ATTACK_SYNC_ENABLED = true ;

    public Boolean USE_ENABLED = false;
    public Integer USE_INTERVAL = 20;
    public Boolean USE_SYNC_ENABLED = true;
}
