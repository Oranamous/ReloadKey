package net.voxelden.reloadkey;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ReloadKey implements ClientModInitializer {
    KeyBinding reloadKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.reload", GLFW.GLFW_KEY_GRAVE_ACCENT, KeyBinding.MISC_CATEGORY));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (reloadKeybind.wasPressed()) {
                if (client.player != null) {
                    client.player.networkHandler.sendChatCommand("reload");
                }
            }
        });
    }
}
