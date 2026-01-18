package com.shanebeestudios.core.api.util;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EntityUtils {

    private static final Map<PlayerRef, Location> LAST_LOCATION_MAP = new HashMap<>();

    public static void teleportPlayer(Store<EntityStore> store, PlayerRef ref, World world, Vector3d position, @Nullable Vector3f rotation) {
        Ref<EntityStore> reference = ref.getReference();
        if (reference == null) return;

        saveLastLocation(ref, world);

        Teleport teleport = new Teleport(world, position, rotation == null ? Vector3f.ZERO : rotation);
        store.addComponent(reference, Teleport.getComponentType(), teleport);
    }

    public static void saveLastLocation(@NotNull PlayerRef ref, @NotNull World world) {
        Transform transform = ref.getTransform().clone();
        Location location = new Location(world, transform.getPosition(), transform.getRotation());
        LAST_LOCATION_MAP.put(ref, location);
    }

    public static boolean teleportToLastLocation(PlayerRef playerRef) {
        Location previousLocation = LAST_LOCATION_MAP.get(playerRef);
        if (previousLocation == null) return false;

        Ref<EntityStore> ref = playerRef.getReference();
        if (ref == null) return false;

        teleportPlayer(playerRef.getReference().getStore(), playerRef, previousLocation.world, previousLocation.position, previousLocation.rotation);
        return true;
    }

    private record Location(World world, Vector3d position, Vector3f rotation) {
    }

}
