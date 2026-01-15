package com.shanebeestudios.core.plugin;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.shanebeestudios.core.plugin.commands.CommandHandler;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.logging.Level;

@SuppressWarnings("unused")
public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;

    public CorePlugin(@NonNullDecl JavaPluginInit init) {
        super(init);
        instance = this;
    }

    @Override
    protected void setup() {
        log("Setting up CorePlugin...");
        CommandHandler.registerCommands(getCommandRegistry());
    }

    @Override
    protected void start() {
    }

    private void log(String message) {
        getLogger().at(Level.INFO).log(message);
    }

    public static CorePlugin getInstance() {
        return instance;
    }

}
