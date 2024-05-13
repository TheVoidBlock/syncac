package com.thevoidblock.syncac;

import com.thevoidblock.syncac.client.SyncacClient;
import com.thevoidblock.syncac.config.ModConfig;
import com.thevoidblock.syncac.gui.ConfigScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static KeyBinding toggleMod;
    public static KeyBinding toggleAttack;
    public static KeyBinding toggleUse;
    public static KeyBinding openMenu;

    public static void registerModKeybindings() {

        openMenu = registerModKeyBinding("open_menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(openMenu.wasPressed()) {
                        SyncacClient.CLIENT.setScreen(ConfigScreen.getConfigScreen(client.currentScreen));
                    }

                }
        );

        toggleMod = registerModKeyBinding("toggle_mod", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleMod.wasPressed()) {
                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED = !AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED;
                        AutoConfig.getConfigHolder(ModConfig.class).save();

                        assert client.player != null;
                        client.player.sendMessage(Text.literal(
                                String.format("Mod Enabled: %s",
                                        Boolean.toString(AutoConfig.getConfigHolder(ModConfig.class).getConfig().MOD_ENABLED).toUpperCase()
                                )
                        ), true);
                    }

                }
        );

        toggleAttack = registerModKeyBinding("toggle_attack", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleAttack.wasPressed()) {
                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED = !AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED;
                        AutoConfig.getConfigHolder(ModConfig.class).save();

                        assert client.player != null;
                        client.player.sendMessage(Text.literal(
                                String.format("Attack Mode: %s, With an Interval of: %s",
                                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_ENABLED.toString().toUpperCase(),
                                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().ATTACK_INTERVAL
                                )
                        ), true);
                    }

                }
        );

        toggleUse = registerModKeyBinding("toggle_use", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleUse.wasPressed()) {
                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED = !AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED;
                        AutoConfig.getConfigHolder(ModConfig.class).save();

                        assert client.player != null;
                        client.player.sendMessage(Text.literal(
                                String.format("Use Mode: %s, With an Interval of: %s",
                                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_ENABLED.toString().toUpperCase(),
                                        AutoConfig.getConfigHolder(ModConfig.class).getConfig().USE_INTERVAL
                                )
                        ), true);
                    }

                }
        );

    }

    private static KeyBinding registerModKeyBinding(String name, InputUtil.Type type, int key, String category) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                    String.format("key.%s.%s", Syncac.MOD_ID, name),
                    type,
                    key,
                    String.format("key.%s.%s", Syncac.MOD_ID, category)
            )
        );
    }

}
