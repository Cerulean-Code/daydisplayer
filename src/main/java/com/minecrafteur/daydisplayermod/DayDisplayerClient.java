package com.minecrafteur.daydisplayermod;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;


import static com.minecrafteur.daydisplayermod.MinecrafteurUtils.MOD_ID;


public class DayDisplayerClient implements net.fabricmc.api.ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientPlayConnectionEvents.JOIN.register(this::onWorldLoad);
    }

    private void onWorldLoad(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {


        MinecrafteurUtils.sendChat(minecraftClient, "§3You are running " + MOD_ID, true);
        MinecrafteurUtils.sendChat(minecraftClient, String.valueOf("§2What the fricking frick" + minecraftClient.world), false);

        MinecrafteurUtils.sendChat(minecraftClient, String.valueOf(MinecrafteurUtils.worldTimeInDays(minecraftClient)));

        // spam the time in chat
        ClientTickEvents.END_CLIENT_TICK.register(this::clientTick);

    }

    private void clientTick(MinecraftClient minecraftClient) {
        MinecrafteurUtils.sendChat(minecraftClient, String.valueOf("§5Day ticks: " + MinecrafteurUtils.worldTimeInDays(minecraftClient)));
    }

}


