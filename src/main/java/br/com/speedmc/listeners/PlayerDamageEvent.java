package br.com.speedmc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import static br.com.speedmc.SpeedPvP.*;

public class PlayerDamageEvent implements Listener {

    // Cancel damage on world cidade
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity().getType() != EntityType.PLAYER) return;
        if (e.getEntity().getWorld() != Bukkit.getWorld(getInstance().getConfig().getString("MundoCidade"))) return;
        e.setCancelled(true);
    }

}
