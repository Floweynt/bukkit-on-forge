package com.floweytf.forgebukkit.command;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import java.util.Map;

public class ForgeBukkitCommandMap extends SimpleCommandMap {

    public ForgeBukkitCommandMap(Server server) {
        super(server);
    }

    public Map<String, Command> getKnownCommands() {
        return knownCommands;
    }
}
