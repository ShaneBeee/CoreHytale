package com.shanebeestudios.core.plugin;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.shanebeestudios.core.api.util.Utils;
import com.shanebeestudios.core.plugin.commands.CommandHandler;
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
    }

    @Override
    protected void start() {
    }

    public static CorePlugin getInstance() {
        return instance;
    }

}
