package br.com.speedmc.listeners;

import br.com.speedmc.SpeedPvP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    @EventHandler
    public void onRightClick(org.bukkit.event.player.PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getClickedBlock().getType() != Material.SKULL) return;
        if (e.getClickedBlock().getX() == 9) {
            if (e.getClickedBlock().getY() == 59) {
                if (e.getClickedBlock().getZ() == -18) {
                    e.getPlayer().performCommand("correio");
                }
            }
        }
    }

}
