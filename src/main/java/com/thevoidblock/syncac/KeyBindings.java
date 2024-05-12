package com.thevoidblock.syncac;

import com.thevoidblock.syncac.settings.AutoClickers;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static KeyBinding toggleMod;

    public static void registerModKeybindings() {

        toggleMod = registerModKeyBinding("main", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "toggle_mod");
        ClientTickEvents.END_CLIENT_TICK.register(
                client -> {

                    while(toggleMod.wasPressed()) {

                        AutoClickers.useAutoClicker.active = !AutoClickers.useAutoClicker.active;
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
