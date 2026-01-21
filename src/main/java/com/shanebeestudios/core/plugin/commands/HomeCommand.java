package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerConfigData;
import com.hypixel.hytale.server.core.entity.entities.player.data.PlayerRespawnPointData;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.EntityUtils;
import com.shanebeestudios.core.api.util.Utils;
import org.jetbrains.annotations.NotNull;

public class HomeCommand extends AbstractPlayerCommand {

    private final OptionalArg<World> worldOptionalArg;

    public HomeCommand() {
        super("home", "Teleport to your bed.");
        addAliases("bed");
        requirePermission("core.commands.home");
        this.worldOptionalArg = withOptionalArg("world", "World to check for bed in.", ArgTypes.WORLD);
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @NotNull Store<EntityStore> store, @NotNull Ref<EntityStore> ref, @NotNull PlayerRef playerRef, @NotNull World world) {
        World targetWorld;
        if (this.worldOptionalArg.provided(commandContext)) {
            targetWorld = this.worldOptionalArg.get(commandContext);
        } else {
            targetWorld = world;
        }
        Player player = playerRef.getComponent(Player.getComponentType());
        assert player != null;
        PlayerConfigData playerConfigData = player.getPlayerConfigData();
        PlayerRespawnPointData[] respawnPoints = playerConfigData.getPerWorldData(targetWorld.getName()).getRespawnPoints();
        if (respawnPoints != null && respawnPoints.length > 0) {
            PlayerRespawnPointData respawnPoint = respawnPoints[0];
            Vector3d pos = respawnPoint.getRespawnPosition();
            EntityUtils.teleportPlayer(store, playerRef, targetWorld, pos, null);
        } else {
            Utils.sendMessage(playerRef, "You have no bed set in '%s'.", targetWorld.getName());
        }
    }

}
