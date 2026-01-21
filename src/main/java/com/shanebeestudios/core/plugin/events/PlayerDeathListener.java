package com.shanebeestudios.core.plugin.events;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentRegistryProxy;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDeathListener extends DeathSystems.OnDeathSystem {

    public PlayerDeathListener(ComponentRegistryProxy<EntityStore> registry) {
        registry.registerSystem(this);
    }

    @Override
    public void onComponentAdded(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent comp, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> buffer) {
        World world = buffer.getExternalData().getWorld();
        PlayerRef playerRef = buffer.getComponent(ref, PlayerRef.getComponentType());
        if (playerRef == null) return;

        EntityUtils.saveLastLocation(playerRef, world);
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return super.componentType();
    }

}
