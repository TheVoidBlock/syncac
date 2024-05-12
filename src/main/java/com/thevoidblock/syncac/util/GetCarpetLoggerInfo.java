package com.thevoidblock.syncac.util;

import com.thevoidblock.syncac.client.SyncacClient;
import com.thevoidblock.syncac.imixin.IPlayerListHudMixin;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCarpetLoggerInfo {

    private static final Pattern PATTERN_CARPET_TPS = Pattern.compile("TPS: (?<tps>[0-9]+[\\.,][0-9]) MSPT: (?<mspt>[0-9]+[\\.,][0-9])");

    private static double getCarpetTPSLoggerInfo(String group) {
        Text playerListFooter = ((IPlayerListHudMixin)SyncacClient.CLIENT.inGameHud.getPlayerListHud()).getFooter();

        if(playerListFooter != null) {
            String[] rows = playerListFooter.getString().split("\n");

            for (String row : rows) {
                Matcher matcher = PATTERN_CARPET_TPS.matcher(row);
                if (matcher.matches()) {
                    return Double.parseDouble(matcher.group(group));
                }
            }
        }
        return -1;
    }

    public static double getTPS() {
        return getCarpetTPSLoggerInfo("tps");
    }
    public static double getMSPT() {
        return getCarpetTPSLoggerInfo("mspt");
    }

}
