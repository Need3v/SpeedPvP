package br.com.speedmc.chain;

import br.com.speedmc.SpeedPvP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;

import static br.com.speedmc.SpeedPvP.*;

public class ChainEvents implements Listener {

    @EventHandler
    public void pressurePlate(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.PHYSICAL)) {
            if (e.getClickedBlock().getType() == Material.WOOD_PLATE) {
                if(e.getPlayer().getWorld() == Bukkit.getWorld("MundoPvP")) {
                    getChain().enteredChain(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onQuit (PlayerQuitEvent e) {
        if (getChain().getOnChain().contains(e.getPlayer())) {
            getChain().exitChain(e.getPlayer());
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (!getChain().getOnChain().contains(e.getPlayer())) return;
        if (e.getFrom().getWorld() != e.getTo().getWorld() || e.getFrom().distance(e.getTo()) > 20) {
            getChain().exitChain(e.getPlayer());
        }
    }

    @EventHandler
    public void onDeath (PlayerDeathEvent e) {
        if (e.getEntity() == null) return;
        Player p = e.getEntity().getPlayer();
        if (getChain().getOnChain().contains(p)) {
            Player killer = e.getEntity().getKiller();
            getChain().killEvent(killer);
            e.getDrops().clear();
            p.getInventory().clear();
            getChain().removeChain(p);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(getInstance(), () -> p.spigot().respawn(), 5L);
    }

}
