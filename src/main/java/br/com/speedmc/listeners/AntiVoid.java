package br.com.speedmc.listeners;

import br.com.speedmc.SpeedRankUP;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiVoid implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getCause() == EntityDamageEvent.DamageCause.VOID)) return;
        Player p = (Player) e.getEntity();
        if (p.getLocation().getBlockY() < 0) {
            World defaultWorld = Bukkit.getWorld(SpeedRankUP.getInstance().getConfig().getString("MundoSpawn"));
            e.setCancelled(true);
            p.sendMessage("§aUfa! Te salvei dessa vez!");
            p.teleport(defaultWorld.getSpawnLocation());
            return;
        }
        e.setCancelled(false);
    }

}
