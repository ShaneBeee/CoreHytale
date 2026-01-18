package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.EntityUtils;
import com.shanebeestudios.core.api.util.Utils;
import org.jetbrains.annotations.NotNull;

public class BackCommand extends AbstractPlayerCommand {

    public BackCommand() {
        super("back", "Go back to your last location.");
        requirePermission("core.commands.back");
    }

    protected void execute(@NotNull CommandContext commandContext, @NotNull Store<EntityStore> store, @NotNull Ref<EntityStore> ref, @NotNull PlayerRef playerRef, @NotNull World world) {
        if (EntityUtils.teleportToLastLocation(playerRef)) {
            Utils.sendMessage(playerRef, "You have teleported to your last location.");
        } else {
            Utils.sendMessage(playerRef, "You have no last location to go back to.");
        }
    }

}
