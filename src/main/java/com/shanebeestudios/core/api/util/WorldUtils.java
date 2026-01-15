package com.shanebeestudios.core.api.util;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.asset.type.fluid.Fluid;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.chunk.section.FluidSection;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;
import org.jetbrains.annotations.Nullable;

public class WorldUtils {

    public static @Nullable Fluid getFluid(World world, int x, int y, int z) {
        Ref<ChunkStore> chunkRef = world.getChunkStore().getChunkSectionReference(ChunkUtil.chunkCoordinate(x), ChunkUtil.chunkCoordinate(y), ChunkUtil.chunkCoordinate(z));
        if (chunkRef == null) {
            return null;
        }
        Store<ChunkStore> store = chunkRef.getStore();
        FluidSection fluidSection = store.getComponent(chunkRef, FluidSection.getComponentType());
        if (fluidSection == null) {
            return null;
        }

        return fluidSection.getFluid(x, y, z);
    }

    public static byte getFluidLevel(World world, int x, int y, int z) {
        Ref<ChunkStore> chunkRef = world.getChunkStore().getChunkSectionReference(ChunkUtil.chunkCoordinate(x), ChunkUtil.chunkCoordinate(y), ChunkUtil.chunkCoordinate(z));
        if (chunkRef == null) {
            return -1;
        }
        Store<ChunkStore> store = chunkRef.getStore();
        FluidSection fluidSection = store.getComponent(chunkRef, FluidSection.getComponentType());
        if (fluidSection == null) {
            return -1;
        }

        return fluidSection.getFluidLevel(x, y, z);
    }

}
