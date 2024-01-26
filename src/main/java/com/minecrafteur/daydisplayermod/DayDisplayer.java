package com.minecrafteur.daydisplayermod;

import net.fabricmc.api.ModInitializer;


public class DayDisplayer implements ModInitializer {

    @Override
    public void onInitialize() {
        new MinecrafteurUtils();
        MinecrafteurUtils.LOGGER.info("Hello Fabric world! main Class");

    }
}