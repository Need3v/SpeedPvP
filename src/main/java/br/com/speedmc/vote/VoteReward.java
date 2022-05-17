package br.com.speedmc.vote;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VoteReward {

    public void giveReward(String username, String service){
        if(Bukkit.getPlayer(username) == null) {
            Bukkit.getConsoleSender().sendMessage("§b§l[Votos] §fErro ao encontrar o jogador: " + username);
            return;
        }
        Player p = Bukkit.getPlayer(username);
        Bukkit.broadcastMessage("§7[§e!§7] §6" + username + " §evotou em §6" +service+ " §ee ganhou §62.000 coins §ee §61 CAP§e. §7(/vote)");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "money add " + username + " 2000");
        if (p.getWorld() != Bukkit.getWorld("Eventos") || p.getWorld() != Bukkit.getWorld("MundoPvP")) {
            p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short)1));
        } else {
            p.sendMessage("§cVocê não recebeu a maçã dourada por votar, pois está em um local não permitido.");
        }
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
    }

}
