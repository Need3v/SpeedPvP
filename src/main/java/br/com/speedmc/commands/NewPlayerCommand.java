package br.com.speedmc.commands;

import static br.com.speedmc.SpeedRankUP.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class NewPlayerCommand implements CommandExecutor {

    private BukkitTask newplayer;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(cmd.getName().equalsIgnoreCase("newplayer"))) return false;
        if (!(sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender)) return false;

        if (args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) return false;
            newplayerExec(p);
        } else {
            sender.sendMessage("§cUso correto: /newplayer (player)");
        }

        return false;
    }

    public void newplayerExec(Player p) {
        newplayer = new BukkitRunnable() {
            @Override
            public void run() {
                for (String me : getInstance().getConfig().getStringList("NovoJogador")) {
                    Bukkit.broadcastMessage(me.replaceAll("&", "§").replaceAll("@player", p.getName()));
                }
                this.cancel();
            }
        }.runTaskTimerAsynchronously(getInstance(), 40, 40);
    }

}
