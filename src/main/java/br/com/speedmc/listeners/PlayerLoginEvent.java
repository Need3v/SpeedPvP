package br.com.speedmc.listeners;

import static br.com.speedmc.SpeedPvP.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLoginEvent implements Listener {

    @EventHandler
    public void JoinEvent (org.bukkit.event.player.PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!p.hasPlayedBefore()) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(getInstance(), new Runnable() {
                @Override
                public void run() {
                    p.chat("/tutorial");
                }
            }, 10L); //20 Tick (1 Second) delay before run() is called
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "asb toggle off " + p.getName());
        } else {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "asb toggle on " + p.getName());
        }

        for (String message : getInstance().getConfig().getStringList("JoinMessages")) {
            if (message.contains("<center>")) {
                p.sendMessage(centerText(message.replaceAll("&", "§").replace("<center>", "").replaceAll("<players>", String.valueOf(Bukkit.getOnlinePlayers().size()))));
            } else {
                p.sendMessage(message.replaceAll("&", "§").replaceAll("<players>", String.valueOf(Bukkit.getOnlinePlayers().size())));
            }
        }
    }

    private String centerText(String text) {
        int maxWidth = 80,
                spaces = (int) Math.round((maxWidth-1.4* ChatColor.stripColor(text).length())/2);
        return StringUtils.repeat(" ", spaces)+text;
    }

}
