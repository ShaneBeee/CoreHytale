package com.shanebeestudios.core.plugin.events;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.shanebeestudios.core.api.util.Utils;

public class EventHandler {

    public static void registerEvents(JavaPlugin plugin) {
        Utils.log("Registering events...");
        new PauseListener(plugin.getEventRegistry());
        new PlayerDeathListener(plugin.getEntityStoreRegistry());
        Utils.log("Registered events.");
    }

}
