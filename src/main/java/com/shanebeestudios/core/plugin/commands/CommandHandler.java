package com.shanebeestudios.core.plugin.commands;

import com.hypixel.hytale.server.core.command.system.CommandRegistry;

public class CommandHandler {

    public static void registerCommands(CommandRegistry commandRegistry) {
        commandRegistry.registerCommand(new AssetsCommand());
        commandRegistry.registerCommand(new BackCommand());
        commandRegistry.registerCommand(new ClearChatcommand());
        commandRegistry.registerCommand(new HomeCommand());
        commandRegistry.registerCommand(new PauseCommand());
        commandRegistry.registerCommand(new RTPCommand());
        commandRegistry.registerCommand(new TestCommand());
        commandRegistry.registerCommand(new TimeCommand());
        commandRegistry.registerCommand(new TopCommand());
    }

}
