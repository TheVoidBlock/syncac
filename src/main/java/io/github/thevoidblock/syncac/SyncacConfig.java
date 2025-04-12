package io.github.thevoidblock.syncac;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Syncac.MOD_ID)
public class SyncacConfig implements ConfigData {
    @ConfigEntry.Category("general")
    public boolean modEnabled = true;

    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.Tooltip
    public String tpsFinderRegex = "TPS: (?<tps>[0-9]+[\\.,][0-9]) MSPT: (?<mspt>[0-9]+[\\.,][0-9])";


    @ConfigEntry.Category("attack")
    public Boolean attackEnabled = false;

    @ConfigEntry.Category("attack")
    @ConfigEntry.Gui.Tooltip
    public Integer attackInterval = 10;

    @ConfigEntry.Category("attack")
    @ConfigEntry.Gui.Tooltip
    public Boolean attackSync = true;

    @ConfigEntry.Category("attack")
    @ConfigEntry.Gui.Tooltip
    public Boolean attackHold = false;


    @ConfigEntry.Category("use")
    public Boolean useEnabled = false;

    @ConfigEntry.Category("use")
    @ConfigEntry.Gui.Tooltip
    public Integer useInterval = 10;

    @ConfigEntry.Category("use")
    @ConfigEntry.Gui.Tooltip
    public Boolean useSync = true;

    @ConfigEntry.Category("use")
    @ConfigEntry.Gui.Tooltip
    public Boolean useHold = false;
}
