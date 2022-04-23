package br.com.speedmc;

import br.com.speedmc.commands.BroadCastCommand;
import br.com.speedmc.commands.ExecuteExtraVIP;
import br.com.speedmc.commands.FeedCommand;
import br.com.speedmc.commands.NewPlayerCommand;
import br.com.speedmc.listeners.AntiVoid;
import br.com.speedmc.listeners.PlayerDamageEvent;
import br.com.speedmc.listeners.PlayerLoginEvent;
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
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedRankUP] §fPlugin successfully initialized.");
    }

    // Actions on plugin disable
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("̛§b[SpeedRankUP] §fPlugin successfully disabled.");
    }

    // Register plugin commands
    public void registerCmds() {
        getCommand("broadcast").setExecutor(new BroadCastCommand());
        getCommand("bcgold").setExecutor(new BroadCastCommand());
        getCommand("executarvipextra").setExecutor(new ExecuteExtraVIP());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("newplayer").setExecutor(new NewPlayerCommand());
    }

    // Register plugin events
    public void registerEvents () {
        Bukkit.getPluginManager().registerEvents(new AntiVoid(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageEvent(), this);
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
