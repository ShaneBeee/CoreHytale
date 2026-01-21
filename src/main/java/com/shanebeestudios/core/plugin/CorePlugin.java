package com.shanebeestudios.core.plugin;

import com.hypixel.hytale.component.ComponentRegistryProxy;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.shanebeestudios.core.api.util.Utils;
import com.shanebeestudios.core.plugin.commands.CommandHandler;
import com.shanebeestudios.core.plugin.events.EventHandler;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

@SuppressWarnings("unused")
public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;

    public CorePlugin(@NonNullDecl JavaPluginInit init) {
        super(init);
        instance = this;
    }

    @Override
    protected void setup() {
        Utils.log("Setting up CorePlugin...");
        CommandHandler.registerCommands(getCommandRegistry());
        EventHandler.registerEvents(this);
        ComponentRegistryProxy<EntityStore> entityStoreRegistry = getEntityStoreRegistry();
    }

    @Override
    protected void start() {
    }

    public static CorePlugin getInstance() {
        return instance;
    }

}
