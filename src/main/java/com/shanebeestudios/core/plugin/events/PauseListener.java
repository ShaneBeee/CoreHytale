package com.shanebeestudios.core.plugin.events;

import com.hypixel.hytale.event.EventRegistry;
import com.hypixel.hytale.server.core.HytaleServer;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.events.StartWorldEvent;
import com.shanebeestudios.core.api.util.Utils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PauseListener {

    public PauseListener(EventRegistry eventRegistry) {
        String defaultWorld = HytaleServer.get().getConfig().getDefaults().getWorld();
        eventRegistry.register(StartWorldEvent.class, defaultWorld, this::onStartWorld);
        eventRegistry.register(PlayerConnectEvent.class, this::onPlayerConnect);
        eventRegistry.register(PlayerDisconnectEvent.class, this::onPlayerDisconnect);
    }

    public void onStartWorld(StartWorldEvent event) {
        Utils.log("Loading and pausing world: '%s'", event.getWorld().getName());
        event.getWorld().setPaused(true);
    }

    public void onPlayerConnect(PlayerConnectEvent event) {
        World world = event.getWorld();
        if (world == null) {
            Utils.error("Error getting world in player connect event.");
            return;
        }
        if (!world.isPaused()) return;

        Utils.log("Unpausing world: '%s'", event.getWorld().getName());

        world.setPaused(false);
    }

    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        PlayerRef playerRef = event.getPlayerRef();
        UUID worldUuid = playerRef.getWorldUuid();
        if (worldUuid == null) {
            Utils.error("Error getting world UUID in player disconnect event.");
            return;
        }
        World world = Universe.get().getWorld(worldUuid);
        if (world == null) {
            Utils.error("Error getting world in player disconnect event.");
            return;
        }
        HytaleServer.SCHEDULED_EXECUTOR.schedule(() -> {
            if (world.getPlayerCount() == 0) {
                Utils.log("Pausing world: '%s'", world.getName());
                world.setPaused(true);
            }
        }, 1, TimeUnit.SECONDS);
    }

}
