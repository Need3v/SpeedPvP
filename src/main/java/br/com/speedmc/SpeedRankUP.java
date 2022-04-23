package br.com.speedmc;

import br.com.speedmc.commands.BroadCastCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpeedRankUP extends JavaPlugin {

    private static SpeedRankUP instance;

    // Actions on plugin enable
    public void onEnable() {
        instance = this;
        loadConfig();
        registerCmds();
        registerEvents();
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedLobby] §fPlugin successfully initialized.");
    }

    // Actions on plugin disable
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedLobby] §fPlugin successfully disabled.");
    }

    // Register plugin commands
    public void registerCmds() {
        getCommand("broadcast").setExecutor(new BroadCastCommand());
        getCommand("bcgold").setExecutor(new BroadCastCommand());
    }

    // Register plugin events
    public void registerEvents () {

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    // get plugin instance
    public static SpeedRankUP getInstance() {
        return instance;
    }
}
