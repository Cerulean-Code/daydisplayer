package com.minecrafteur.daydisplayermod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class DayDisplayerHelperUtils {

    public static void sendModNameInChat(MinecraftClient minecraftClient) {
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Gold", "") + "\uD83C\uDF1E Day Displayer");
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");
    }


    public static void unlockInitialAdvancements(MinecraftServer server, ServerPlayerEntity player) {
        // mod installed
        MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:installed");

        // is in hardcore
        if (isHardcore(server)) {
            MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:playing_hardcore");
        }

    }


    public static void unlockTimeAdvancements(long currentDay, MinecraftServer server, ServerPlayerEntity player) {
        if (isHardcore(server)) {

            if (currentDay >= 1) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_1");
            }

            if (currentDay >= 5) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_5");
            }

            if (currentDay >= 10) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_10");
            }
            if (currentDay >= 15) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_15");
            }
            if (currentDay >= 30) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_30");
            }
            if (currentDay >= 50) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_50");
            }
            if (currentDay >= 100) {
                MinecrafteurUtils.grantAdvancement(player, "daydisplayermod:survive_100");
            }
        }
    }


    // conditions for advancements
    public static boolean isHardcore(MinecraftServer server) {
        return server.isHardcore();

    }
}
