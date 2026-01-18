package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerConfigData;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerRespawnPointData;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.EntityUtils;
import org.jetbrains.annotations.NotNull;

public class HomeCommand extends AbstractPlayerCommand {

    public HomeCommand() {
        super("home", "Teleport to your bed.");
        requirePermission("core.commands.home");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @NotNull Store<EntityStore> store, @NotNull Ref<EntityStore> ref, @NotNull PlayerRef playerRef, @NotNull World world) {
        Player player = playerRef.getComponent(Player.getComponentType());
        assert player != null;
        PlayerConfigData playerConfigData = player.getPlayerConfigData();
        PlayerRespawnPointData[] respawnPoints = playerConfigData.getPerWorldData(world.getName()).getRespawnPoints();
        if (respawnPoints != null && respawnPoints.length > 0) {
            PlayerRespawnPointData respawnPoint = respawnPoints[0];
            Vector3d pos = respawnPoint.getRespawnPosition();
            EntityUtils.teleportPlayer(store, playerRef, world, pos, null);
        }
    }

}
