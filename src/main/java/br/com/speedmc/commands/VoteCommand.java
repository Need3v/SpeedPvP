package br.com.speedmc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static br.com.speedmc.SpeedPvP.*;

public class VoteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("vote")) return false;

        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("start")) {

            }
        }

        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        if (args.length != 1) {
            p.sendMessage("");
            p.sendMessage(" §e§lVotos");
            p.sendMessage("");
            p.sendMessage(" §a/vote links §7- §fLinks para votar no servidor.");
            p.sendMessage(" §a/vote party §7- §fVeja a Vote Party atual.");
            p.sendMessage("");
            return false;
        } else {
            if (args[0].equalsIgnoreCase("links")) {
                p.sendMessage("");
                p.sendMessage(" §e§lLinks para votar");
                p.sendMessage("");
                for(String message : getInstance().getConfig().getStringList("VotesLinks")) {
                    p.sendMessage(" §a"+message);
                }
                p.sendMessage("");
                return false;
            }

            if (args[0].equalsIgnoreCase("party")) {
                p.sendMessage("");
                p.sendMessage(" §d§lVote Party");
                p.sendMessage(" §fAo atingir 60 votos no servidor, a Vote");
                p.sendMessage(" §fParty será iniciada, onde todos online");
                p.sendMessage(" §freceberão uma Caixa Lendária.");
                p.sendMessage("");
                p.sendMessage("§fVote Party atual: §a" + getVoteParty().getVotes() + "/60");
                p.sendMessage("");
                return false;
            }
        }

        return false;
    }
}
