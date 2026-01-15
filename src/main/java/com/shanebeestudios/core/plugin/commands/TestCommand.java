package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.asset.type.fluid.Fluid;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import com.shanebeestudios.core.api.util.WorldUtils;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class TestCommand extends AbstractPlayerCommand {

    public TestCommand() {
        super("test", "A test command");
        requirePermission("core.commands.test");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Vector3i pos = playerRef.getTransform().getPosition().toVector3i();

        Fluid fluid = WorldUtils.getFluid(world, pos.getX(), pos.getY(), pos.getZ());
        if (fluid == null) fluid = Fluid.EMPTY;

        byte level = WorldUtils.getFluidLevel(world, pos.getX(), pos.getY(), pos.getZ());
        Utils.sendMessage(playerRef, "Fluid ID: " + fluid.getId() + "/ Level: " + level);

    }

}
