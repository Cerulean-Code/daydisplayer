package com.minecrafteur.daydisplayermod;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class MinecrafteurUtils {
    protected static String MOD_ID;
    protected static Logger LOGGER;
    protected static HashMap<String, String> colors;


    public MinecrafteurUtils() {
        MOD_ID = "daydisplayermod";
        LOGGER = LoggerFactory.getLogger(MOD_ID);

        colors = new HashMap<>();
        colors.put("Black", "§0");
        colors.put("Dark Blue", "§1");
        colors.put("Dark Green", "§2");
        colors.put("Dark Aqua", "§3");
        colors.put("Dark Red", "§4");
        colors.put("Purple", "§5");
        colors.put("Gold", "§6");
        colors.put("Gray", "§7");
        colors.put("Dark Gray", "§8");
        colors.put("Indigo", "§9");
        colors.put("Bright Green", "§a");
        colors.put("Aqua", "§b");
        colors.put("Red", "§c");
        colors.put("Pink", "§d");
        colors.put("Yellow", "§e");
        colors.put("White", "§f");
    }


    /**
     * Checks if the player is currently in the Minecraft world.
     *
     * @param minecraftClient The instance of the Minecraft client.
     * @return True if the player is in the world and not null; otherwise, false.
     */
    private static Boolean isPlayerInWorld(MinecraftClient minecraftClient) {
        return minecraftClient.world != null && minecraftClient.player != null;
    }

    /**
     * Sends a chat message to the player.
     *
     * @param minecraftClient The instance of the Minecraft client.
     * @param message         The message to be sent to the player.
     * @param asOverlay       Determines whether the message should be displayed as an overlay.
     * @return True if the message was successfully sent; otherwise, false.
     */
    public static Boolean sendChat(MinecraftClient minecraftClient, String message, Boolean asOverlay) {
        if (isPlayerInWorld(minecraftClient)) {
            minecraftClient.player.sendMessage(Text.of(message), asOverlay);
            return true;
        } else {
            LOGGER.info("Cannot send message. Player not in the world.");
            return false;
        }
    }

    public static Boolean sendChat(MinecraftClient minecraftClient, String message) {
        return sendChat(minecraftClient, message, false);
    }


    public static long worldTime(MinecraftClient minecraftClient) {
        try {
            ClientWorld world = minecraftClient.world;
            if (world != null) {
                return world.getTimeOfDay();
            } else {
                LOGGER.error("ClientWorld is null");
            }

        } catch (Exception e) {

            LOGGER.error(String.valueOf(e));
        }
        return -1;
    }

    public static WorldTimePOJO getWorldTime(MinecraftClient minecraftClient) {
        return new WorldTimePOJO(worldTime(minecraftClient));
    }

    public static void sendBlankChat(MinecraftClient client) {
        MinecrafteurUtils.sendChat(client, "");
    }

    public static void showDay(MinecraftClient client) {
        DayDisplayerHelperUtils.sendModNameInChat(client);
        MinecrafteurUtils.sendChat(client, MinecrafteurUtils.colors.getOrDefault("White", "") + "Current Day: " + MinecrafteurUtils.getWorldTime(client).getDay());
    }

    public static void showFullDay(MinecraftClient client) {
        showDay(client);
        MinecrafteurUtils.sendChat(client, MinecrafteurUtils.colors.getOrDefault("White", "") + "Time: " + MinecrafteurUtils.getWorldTime(client).getHours() + ":" + MinecrafteurUtils.getWorldTime(client).getMinutes() + ":" + MinecrafteurUtils.getWorldTime(client).getSeconds());
        sendBlankChat(client);
    }


}
