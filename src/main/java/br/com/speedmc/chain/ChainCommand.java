package br.com.speedmc.chain;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

import static br.com.speedmc.SpeedPvP.*;

public class ChainCommand implements CommandExecutor {

    private HashMap<String, Location> locs;

    public ChainCommand() {
        locs = getInstance().getLocs();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setchain")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("speedpvp.setchain")) {

                    if (args.length == 0) {
                        p.sendMessage("§cUse: /setchain <local> (entrada, saida)");
                        return false;
                    }

                    if (args[0].equalsIgnoreCase("entrada")) {
                        Location loc = p.getLocation();
                        if (getInstance().getLocs().containsKey("chain")) {
                            locs.remove("chain");
                        }
                        locs.put("chain", loc);
                        getLocsFile().getConfig().set("Locs.chain", p.getWorld().getName()+","+p.getLocation().getX()+","+p.getLocation().getY()+","+p.getLocation().getZ()+","+p.getLocation().getPitch()+","+p.getLocation().getYaw());
                        getLocsFile().saveConfig();
                        p.sendMessage("§aLocal setado com sucesso!");
                    } else if (args[0].equalsIgnoreCase("saida")) {
                        Location loc = p.getLocation();
                        if (getInstance().getLocs().containsKey("chainsaida")) {
                            locs.remove("chainsaida");
                        }
                        locs.put("chainsaida", loc);
                        getLocsFile().getConfig().set("Locs.chainsaida", p.getWorld().getName()+","+p.getLocation().getX()+","+p.getLocation().getY()+","+p.getLocation().getZ()+","+p.getLocation().getPitch()+","+p.getLocation().getYaw());
                        getLocsFile().saveConfig();
                        p.sendMessage("§aLocal setado com sucesso!");
                    }
                }
            }
        } else if (cmd.getName().equalsIgnoreCase("chain")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                getChain().joinChain(p);
            }
        }
        return false;
    }

}
