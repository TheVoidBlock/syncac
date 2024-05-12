package com.thevoidblock.syncac.client;

import com.thevoidblock.syncac.KeyBindings;
import com.thevoidblock.syncac.Variables;
import com.thevoidblock.syncac.imixin.IPlayerListHudMixin;
import com.thevoidblock.syncac.Syncac;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyncacClient implements ClientModInitializer {
    public MinecraftClient client = MinecraftClient.getInstance();
    private static final Pattern PATTERN_CARPET_TPS = Pattern.compile("TPS: (?<tps>[0-9]+[\\.,][0-9]) MSPT: (?<mspt>[0-9]+[\\.,][0-9])");

    @Override
    public void onInitializeClient() {

        KeyBindings.registerModKeybindings();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.player != null) {
                Variables.syncCooldown = (int)Math.max(Variables.cooldown*(20/getTPS()), Variables.syncCooldown);
                if (Variables.timeLeft >= Variables.syncCooldown) {
                    if (client.targetedEntity != null)
                        client.interactionManager.attackEntity(client.player, client.targetedEntity);
                    client.player.swingHand(Hand.MAIN_HAND);

                    Variables.timeLeft = 0;
                    Variables.syncCooldown = Variables.cooldown;
                } else {
                    Variables.timeLeft++;
                }
            }
        });

        Syncac.LOGGER.info(String.format("clickity clack clack (%s client initialized)", Syncac.MOD_ID));
    }

    private double getTPS() {
        Text playerListFooter = ((IPlayerListHudMixin)client.inGameHud.getPlayerListHud()).getFooter();
        if(playerListFooter != null) {
            String[] lines = playerListFooter.getString().split("\n");

            for (String line : lines) {
                Matcher matcher = PATTERN_CARPET_TPS.matcher(line);
                if (matcher.matches()) {
                    return Math.floor(Double.parseDouble(matcher.group("tps")));
                }
            }
        }
        return 20;
    }
}
