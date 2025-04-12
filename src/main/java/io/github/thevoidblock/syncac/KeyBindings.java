package io.github.thevoidblock.syncac;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import static io.github.thevoidblock.syncac.Syncac.MOD_ID;
import static java.lang.String.format;

public class KeyBindings {
    public static String DEFAULT_CATEGORY = MOD_ID;

    public static KeyBinding toggleMod;
    public static KeyBinding toggleAttack;
    public static KeyBinding toggleUse;
    public static KeyBinding openMenu;

    public static void registerKeybindings() {

        openMenu = registerKeyBinding("open_menu");
        toggleMod = registerKeyBinding("toggle_mod");
        toggleAttack = registerKeyBinding("toggle_attack");
        toggleUse = registerKeyBinding("toggle_use");

        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {
                    while(openMenu.wasPressed()) {
                        Syncac.CLIENT.setScreen(Syncac.getConfigScreen());
                    }

                    while(toggleMod.wasPressed()) {
                        Syncac.getConfig().modEnabled = !Syncac.getConfig().modEnabled;
                        Syncac.saveConfig();

                        assert client.player != null;
                        client.player.sendMessage(Text.translatable(
                                "alert.syncac.toggle_syncac",
                                Boolean.toString(Syncac.getConfig().modEnabled).toUpperCase()
                        ), true);
                    }

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

    private static KeyBinding registerKeyBinding(String name) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                    format("key.%s.%s", MOD_ID, name),
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_UNKNOWN,
                    format("key.%s.category.%s", MOD_ID, DEFAULT_CATEGORY)
            )
        );
    }
}
