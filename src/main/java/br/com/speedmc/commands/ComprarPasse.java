package br.com.speedmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComprarPasse implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("comprarpasse")) return false;
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        p.chat("/passepremium");
        return false;
    }
}
