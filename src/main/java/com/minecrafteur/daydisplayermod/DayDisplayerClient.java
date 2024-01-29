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
    public static KeyBinding dayKeyBinding;
    public static KeyBinding dayFullKeyBinding;

    public static int tickCount = 0;
    public static int timeChecked = -1;
    public static long currentDay = -1;


    @Override
    public void onInitializeClient() {
        createKeyBindings();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            // key bind for showing the simple day
            while (dayKeyBinding.wasPressed()) {
                MinecrafteurUtils.showDay(client);
                MinecrafteurUtils.sendChat(client, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");

            }

            // key bind for showing the detailed day
            while (dayFullKeyBinding.wasPressed()) {
                MinecrafteurUtils.showFullDay(client);
                MinecrafteurUtils.sendChat(client, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");

            }
        });


        // Immediately runs when player joins the world
        ClientPlayConnectionEvents.JOIN.register(this::onWorldLoad);
    }


    private void onWorldLoad(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (tickCount >= 20 && timeChecked < 1) {
                timeChecked++;
                tickCount = 0;
                currentDay = MinecrafteurUtils.getWorldTime(client).getDay();
            } else if (tickCount >= 20) {
                long worldDay = MinecrafteurUtils.getWorldTime(client).getDay();
                MinecrafteurUtils.sendChat(client, "World day = " + worldDay + "      current day = " + currentDay);
                if (worldDay > currentDay) {
                    currentDay = worldDay;
                    MinecrafteurUtils.showDay(client);
                }
                tickCount = 0;
            }
            tickCount++;

        });
    }

    public static void createKeyBindings() {
        dayKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.daydisplayer.showday", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PAGE_UP, "category.daydisplayer.binding"));
        dayFullKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.daydisplayer.showfullday", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_PAGE_DOWN, "category.daydisplayer.binding"));
    }
}


