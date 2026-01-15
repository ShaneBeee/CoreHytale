package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.asset.type.fluid.Fluid;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import com.shanebeestudios.core.api.util.WorldUtils;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Random;

public class RTPCommand extends AbstractPlayerCommand {

    private final Random random = new Random();

    public RTPCommand() {
        super("rtp", "Teleport to a random location in the world.");
        addAliases("randomteleport");
        requirePermission("core.commands.rtp");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store,
                           @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef,
                           @NonNullDecl World world) {

        tryTeleport(world, playerRef, ref, store, 1);
    }

    private void tryTeleport(World world, PlayerRef playerRef, Ref<EntityStore> ref, Store<EntityStore> store, int tries) {
        if (tries > 10) {
            Utils.sendMessage(playerRef, "Failed to teleport you.");
            return;
        }
        int x = this.random.nextInt(-100000, 100000);
        int z = this.random.nextInt(-100000, 100000);

        world.getChunkAsync(x >> 5, z >> 5).thenAccept(worldChunk -> {
            short height = worldChunk.getHeight(x, z);
            Fluid fluid = WorldUtils.getFluid(world, x, height + 1, z);
            if (fluid == null || fluid == Fluid.EMPTY) {
                Transform transform = playerRef.getTransform();
                transform.setPosition(new Vector3d(x + 0.5, height + 1, z + 0.5));
                Teleport teleport = new Teleport(transform).withHeadRotation(playerRef.getHeadRotation());
                store.addComponent(ref, Teleport.getComponentType(), teleport);
                Utils.sendMessage(playerRef, "You have been teleported to a random location.");
            } else {
                Utils.sendMessage(playerRef, "Trying to find another safe space.");
                tryTeleport(world, playerRef, ref, store, tries + 1);
            }
        });
    }

}
