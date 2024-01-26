package com.minecrafteur.daydisplayermod;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DayDisplayerClient implements net.fabricmc.api.ClientModInitializer {
    public static KeyBinding keyBinding;


    @Override
    public void onInitializeClient() {
        // create a key bind to show the day in the chat [ENTER]
        createKeyBinding();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed())
                MinecrafteurUtils.sendChat(client, String.valueOf("§5Day ticks: " + MinecrafteurUtils.worldTimeInDays(client)));
        });

        // Immediately runs when player joins the world
        ClientPlayConnectionEvents.JOIN.register(this::onWorldLoad);
    }


    private void onWorldLoad(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {

    }


    public static void createKeyBinding() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.daydisplayer.show", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_ENTER, "category.daydisplayer.test"));
    }
}


