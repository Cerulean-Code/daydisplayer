package com.minecrafteur.daydisplayermod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// time of 1 mc day in ticks 24000
public class MinecrafteurUtils {
    // reading fabric json

    protected static String MOD_ID;
    protected static Logger LOGGER;

    public MinecrafteurUtils() {
        MOD_ID = "daydisplayermod";
        LOGGER = LoggerFactory.getLogger(MOD_ID);


    }


    /**
     * Checks if the player is currently in the Minecraft world.
     *
     * @param minecraftClient The instance of the Minecraft client.
     * @return True if the player is in the world and not null; otherwise, false.
     */
    public static Boolean isPlayerInWorld(MinecraftClient minecraftClient) {
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

    public static MinecraftServer getServerWorld(MinecraftClient minecraftClient) {
        if (minecraftClient.isConnectedToLocalServer()) {
            MinecraftServer minecraftServer = minecraftClient.getServer();

            if (minecraftServer != null) {
                return minecraftServer;
            }
        }

        return null;
    }

    public static long worldTime(MinecraftClient minecraftClient) {
        try {
            MinecraftServer server = getServerWorld(minecraftClient);
            return server.getWorld(World.OVERWORLD).getTimeOfDay();

        } catch (Exception e) {

            LOGGER.error(String.valueOf(e));
        }
        return -1;
    }

    public static int worldTimeInDays(MinecraftClient minecraftClient) {
//        long timeDay = worldTime(minecraftClient);
//        long day = (int) (timeDay / 24000);
//        int dayTicks = (int) (timeDay % 24000);
//        int hour = ((dayTicks / 1000) + 6) % 24;
//        int minutes = (int) (dayTicks / 16.666666) % 60;
//        int seconds = (int) (dayTicks / 0.277777) % 60;

        long timeDay = worldTime(minecraftClient);
        long day = (int) (timeDay / 24000);
        int dayTicks = (int) (timeDay % 24000);
        int hour = ((dayTicks / 1000) + 6) % 24;
        int minutes = (int) ((dayTicks % 1000) / 16.666666);
        int seconds = (int) ((dayTicks % 16.666666) / 0.277777);

        sendChat(minecraftClient, String.valueOf(timeDay));
        sendChat(minecraftClient, "§2 day: " + day + " hour: " + hour + " min: " + minutes + " sec: " + seconds);
//        sendChat(minecraftClient, "§2timeDay: " + String.valueOf(timeDay));
//        sendChat(minecraftClient, "§2day: " + String.valueOf(day));
//        sendChat(minecraftClient, "§2dayTicks" + String.valueOf(dayTicks));
//        sendChat(minecraftClient, "§2hour" + String.valueOf(hour));
//        sendChat(minecraftClient, "§2minutes" + String.valueOf(minutes));
//        sendChat(minecraftClient, "§2seconds" + String.valueOf(seconds));


        return dayTicks;
    }
}