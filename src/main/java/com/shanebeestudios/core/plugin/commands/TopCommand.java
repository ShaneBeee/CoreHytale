package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class TopCommand extends AbstractPlayerCommand {

    public TopCommand() {
        super("top", "Teleport to the top of the world.");
        requirePermission("core.commands.top");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Transform transform = playerRef.getTransform();
        Vector3d position = transform.getPosition();
        double x = position.getX();
        double z = position.getZ();

        long index = ChunkUtil.indexChunkFromBlock(x, z);
        WorldChunk chunk = world.getChunkIfLoaded(index);
        if (chunk == null) {
            throw new IllegalStateException("Chunk not loaded!");
        }
        short height = chunk.getHeight((int) x, (int) z);

        Teleport teleport = new Teleport(world, new Vector3d(x, height + 1, z), Vector3f.ZERO);
        store.addComponent(ref, Teleport.getComponentType(), teleport);
        playerRef.sendMessage(Message.raw("Teleporting..."));

    }

}
