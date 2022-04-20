package br.com.speedmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpeedLobby extends JavaPlugin {

    // Actions on plugin enable
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedLobby] §fPlugin successfully initialized.");
    }

    // Actions on plugin disable
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedLobby] §fPlugin successfully disabled.");
    }

    // Register plugin commands
    public void registerCmds() {

    }

    // Register plugin events
    public void registerEvents () {

    }

}
