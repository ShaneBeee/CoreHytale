package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.modules.time.WorldTimeResource;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeCommand extends AbstractPlayerCommand {

    protected TimeCommand() {
        super("now", "Prints the current time in a nicer format.");
    }


    @Override
    protected void execute(@NotNull CommandContext commandContext, @NotNull Store<EntityStore> store, @NotNull Ref<EntityStore> ref, @NotNull PlayerRef playerRef, @NotNull World world) {
        WorldTimeResource worldTimeResource = store.getResource(WorldTimeResource.getResourceType());
        Instant gameTime = worldTimeResource.getGameTime();
        String format = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss")
            .withZone(ZoneId.systemDefault())
            .format(gameTime);
        Utils.sendMessage(playerRef, "The time is " + format);
    }

}
