package com.shanebeestudios.core.api.util;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.receiver.IMessageReceiver;
import com.shanebeestudios.core.plugin.CorePlugin;

import java.awt.*;
import java.util.logging.Level;

public class Utils {

    private static final Color BRACKET_COLOR = new Color(115, 110, 110);
    private static final Color CORE_NAME_COLOR = new Color(27, 169, 140);
    private static final Message CORE_PREFIX = Message.raw("[").color(BRACKET_COLOR)
        .insert(Message.raw("Core").color(CORE_NAME_COLOR))
        .insert(Message.raw("] ").color(BRACKET_COLOR));

    /**
     * Send a message to a receiver.
     * Message will be prefixed
     *
     * @param receiver Receiver to send a message to
     * @param message  Message to send
     */
    public static void sendMessage(IMessageReceiver receiver, String message) {
        Message mess = Message.empty().insert(CORE_PREFIX).insert(Message.raw(message));
        receiver.sendMessage(mess);
    }

    public static void log(String message) {
        CorePlugin.getInstance().getLogger().at(Level.INFO).log( message);
    }

    public static void error(String message) {
        CorePlugin.getInstance().getLogger().at(Level.SEVERE).log( message);
    }

    public static void warn(String message) {
        CorePlugin.getInstance().getLogger().at(Level.WARNING).log(message);
    }

}
