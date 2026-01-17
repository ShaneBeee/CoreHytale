package com.shanebeestudios.core.plugin.events;

import com.hypixel.hytale.event.EventRegistry;
import com.shanebeestudios.core.api.util.Utils;

public class EventHandler {

    public static void registerEvents(EventRegistry eventRegistry) {
        Utils.log("Registering events...");
        new PauseListener(eventRegistry);
        Utils.log("Registered events.");
    }

}
