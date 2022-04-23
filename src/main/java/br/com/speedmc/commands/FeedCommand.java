package br.com.speedmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("feed")) return false;
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (!p.hasPermission("speedmc.feed")) return false;
        p.setFoodLevel(20);
        p.setSaturation(20);
        p.sendMessage("§aAgora você está de barriga cheia, delicia!");
        return false;
    }
}
