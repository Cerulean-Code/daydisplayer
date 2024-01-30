package com.minecrafteur.daydisplayermod;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.apache.logging.log4j.core.jmx.Server;
import org.lwjgl.glfw.GLFW;

public class DayDisplayerClient implements net.fabricmc.api.ClientModInitializer {
    public static KeyBinding dayKeyBinding;
    public static KeyBinding dayFullKeyBinding;

    //time data
    public static int tickCount = 0;
    public static int timeChecked = -1;
    public static long currentDay = -1;

    // advancements

    public static boolean isServerLoaded = false;

    //client info
    public static String uuid = "";

    //server world
    public static MinecraftServer MINECRAFTSERVER;

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
        ServerWorldEvents.LOAD.register(this::onServerWorldLoad);
    }

    private void onServerWorldLoad(MinecraftServer minecraftServer, ServerWorld serverWorld) {
        MINECRAFTSERVER = minecraftServer;

    }


    private void onWorldLoad(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        // Auto day display on new day
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (tickCount >= 20 && timeChecked < 1) {
                timeChecked++;
                tickCount = 0;
                currentDay = MinecrafteurUtils.getWorldTime(client).getDay();
            } else if (tickCount >= 20) {
                // unlock advancements
                try {
                    DayDisplayerHelperUtils.unlockInitialAdvancements(MINECRAFTSERVER, MINECRAFTSERVER.getPlayerManager().getPlayer(client.player.getUuid()));
                    DayDisplayerHelperUtils.unlockTimeAdvancements(currentDay, MINECRAFTSERVER, MINECRAFTSERVER.getPlayerManager().getPlayer(client.player.getUuid()));

                } catch (Exception e) {
                    MinecrafteurUtils.LOGGER.info(String.valueOf(e));
                }
                long worldDay = MinecrafteurUtils.getWorldTime(client).getDay();
                if (worldDay > currentDay) {
                    currentDay = worldDay;
                    MinecrafteurUtils.showDay(client);
                    MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");

                    DayDisplayerHelperUtils.unlockTimeAdvancements(currentDay, MINECRAFTSERVER, MINECRAFTSERVER.getPlayerManager().getPlayer(client.player.getUuid()));
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


