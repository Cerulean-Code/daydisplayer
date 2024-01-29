package com.minecrafteur.daydisplayermod;

import net.minecraft.client.MinecraftClient;

public class DayDisplayerHelperUtils {

    public static void sendModNameInChat(MinecraftClient minecraftClient) {
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Gold", "") + "\uD83C\uDF1E Day Displayer");
        MinecrafteurUtils.sendChat(minecraftClient, MinecrafteurUtils.colors.getOrDefault("Dark Gray", "") + "-----------------");
    }
}
