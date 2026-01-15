package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractWorldCommand;
import com.hypixel.hytale.server.core.modules.time.WorldTimeResource;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeCommand extends AbstractWorldCommand {

    protected TimeCommand() {
        super("now", "Prints the current time in a nicer format.");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @NotNull World world, @NotNull Store<EntityStore> store) {
        WorldTimeResource worldTimeResource = store.getResource(WorldTimeResource.getResourceType());
        Instant gameTime = worldTimeResource.getGameTime();

        String pausedMessage = world.getWorldConfig().isGameTimePaused() ? "paused" : "unpaused";
        String name = world.getName();
        String format = DateTimeFormatter.ofPattern("u/MM/dd h:mm:ss a")
            .withZone(ZoneId.of("UTC"))
            .format(gameTime);
        Utils.sendMessage(commandContext.sender(), "The [" + pausedMessage + "] time in '" + name + "' is " + format);
    }

}
