package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractWorldCommand;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import org.jetbrains.annotations.NotNull;

public class PauseCommand extends AbstractWorldCommand {

    protected PauseCommand() {
        super("pause", "Pauses the game.");
        requirePermission("core.commands.pause");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @NotNull World world, @NotNull Store<EntityStore> store) {
        world.setPaused(!world.isPaused());
        String paused = world.isPaused() ? "paused" : "unpaused";
        Utils.sendMessage(commandContext.sender(), "World '%s' is now %s", world.getName(), paused);
    }

}
