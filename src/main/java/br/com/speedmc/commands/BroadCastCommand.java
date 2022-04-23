package br.com.speedmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import static br.com.speedmc.SpeedRankUP.*;

public class BroadCastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (sender instanceof Player) {
            if (!(sender.hasPermission("speedmc.broadcast"))) return false;
            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (args.length < 1) {
                    sender.sendMessage("§e§lINFO ➜ §fUso correto: /bc <mensagem>");
                    return false;
                }
                String message = "&6";
                for (int i = 0; i != args.length; i++) message += args[i] + " ";
                String prefix = getInstance().getConfig().getString("PrefixBroadCast").replace("&", "§");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(prefix + message.replace("&", "§"));
                Bukkit.broadcastMessage("");
                return true;
            } else if (cmd.getName().equalsIgnoreCase("bcgold")) {
                if (args.length < 1) {
                    sender.sendMessage("§e§lINFO ➜ §fUso correto: /bcgold <mensagem>");
                    return false;
                }
                String message = "&6";
                for (int i = 0; i != args.length; i++) message += args[i] + " ";
                String prefix = getInstance().getConfig().getString("PrefixBroadCastGold").replace("&", "§");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(prefix + message.replace("&", "§"));
                Bukkit.broadcastMessage("");
                return true;
            }
        } else if (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender) {
            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (args.length < 1) {
                    sender.sendMessage("§e§lINFO ➜ §fUso correto: /bc <mensagem>");
                    return false;
                }
                String message = "&6";
                for (int i = 0; i != args.length; i++) message += args[i] + " ";
                String prefix = getInstance().getConfig().getString("PrefixBroadCast").replace("&", "§");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(prefix + message.replace("&", "§"));
                Bukkit.broadcastMessage("");
                return true;
            } else if (cmd.getName().equalsIgnoreCase("bcgold")) {
                if (args.length < 1) {
                    sender.sendMessage("§e§lINFO ➜ §fUso correto: /bcgold <mensagem>");
                    return false;
                }
                String message = "&6";
                for (int i = 0; i != args.length; i++) message += args[i] + " ";
                String prefix = getInstance().getConfig().getString("PrefixBroadCastGold").replace("&", "§");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(prefix + message.replace("&", "§"));
                Bukkit.broadcastMessage("");
                return true;
            }
        }
        return false;
    }

}
