package com.thevoidblock.syncac.util;

import com.thevoidblock.syncac.SyncacConfig;
import com.thevoidblock.syncac.mixin.PlayerListHudAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thevoidblock.syncac.Syncac.CLIENT;

public class GetCarpetLoggerInfo {

    private static final SyncacConfig config = AutoConfig.getConfigHolder(SyncacConfig.class).getConfig();
    public static Pattern PATTERN_CARPET_TPS = Pattern.compile(config.TPS_FINDER_REGEX);

    private static double getCarpetTPSLoggerInfo(String group) {

        Text playerListFooter = ((PlayerListHudAccessor) CLIENT.inGameHud.getPlayerListHud()).getFooter();

        if(playerListFooter != null) {
            String[] rows = playerListFooter.getString().split("\n");

            for (String row : rows) {
                Matcher matcher = PATTERN_CARPET_TPS.matcher(row);
                if (matcher.matches()) {
                    return Double.parseDouble(matcher.group(group));
                }
            }
        }
        if(group.equals("mspt")) return 50;
        else return 20;
    }

    public static double getTPS() {
        return getCarpetTPSLoggerInfo("tps");
    }
    public static double getMSPT() {
        return getCarpetTPSLoggerInfo("mspt");
    }

}
