package com.thevoidblock.syncac;

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
                        Syncac.CLIENT.setScreen(Syncac.getConfigScreen());
                    }

                }
        );

        toggleMod = registerModKeyBinding("toggle_mod", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleMod.wasPressed()) {
                        Syncac.getConfig().modEnabled = !Syncac.getConfig().modEnabled;
                        Syncac.saveConfig();

                        assert client.player != null;
                        client.player.sendMessage(Text.translatable(
                                "alert.syncac.toggle_syncac",
                                Boolean.toString(Syncac.getConfig().modEnabled).toUpperCase()
                        ), true);
                    }

                }
        );

        toggleAttack = registerModKeyBinding("toggle_attack", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleAttack.wasPressed()) {
                        Syncac.getConfig().attackEnabled = !Syncac.getConfig().attackEnabled;
                        Syncac.saveConfig();

                        assert client.player != null;
                        client.player.sendMessage(Text.translatable(
                                "alert.syncac.toggle_attack",
                                Syncac.getConfig().attackEnabled.toString().toUpperCase(),
                                Syncac.getConfig().attackInterval
                        ), true);
                    }

                }
        );

        toggleUse = registerModKeyBinding("toggle_use", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "main");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(toggleUse.wasPressed()) {
                        Syncac.getConfig().useEnabled = !Syncac.getConfig().useEnabled;
                        Syncac.saveConfig();

                        assert client.player != null;
                        client.player.sendMessage(Text.translatable(
                                "alert.syncac.toggle_use",
                                Syncac.getConfig().useEnabled.toString().toUpperCase(),
                                Syncac.getConfig().useInterval
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
