package br.com.speedmc.commands;

import static br.com.speedmc.SpeedPvP.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ExecuteExtraVIP implements CommandExecutor {

    private BukkitTask execvip;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("executarvipextra")) {
            if (sender instanceof ConsoleCommandSender || sender instanceof RemoteConsoleCommandSender) {
                if (args.length < 2) {
                    sender.sendMessage("§fUso correto: /executarvipextra <jogador> <vip>");
                    return false;
                }
                Player p = Bukkit.getPlayer(args[0]);
                if (p == null) {
                    sender.sendMessage("§fJogador não encontrado.");
                    return false;
                }
                String comando = getInstance().getConfig().getString("comando").replace("{player}", p.getName()).replace("{vip}", args[1]);
                double d = Math.random() * 100;
                if (d < getInstance().getConfig().getInt("chance")) {
                    p.sendMessage("");
                    p.sendMessage("§e§lVIPS ➜ §fVocê foi o sortudo da vez! Preparando entrega do seu VIP Extra.");
                    p.sendMessage("");
                    Bukkit.getConsoleSender().sendMessage("§b[SpeedMC] §fO jogador "+ p.getName() + " ganhou o VIP Extra!");
                    execvip(p, comando);
                } else {
                    Bukkit.getConsoleSender().sendMessage("§b[SpeedMC] §fO jogador "+ p.getName() + " não ganhou o VIP Extra desta vez :(");
                }
            }
        }
        return false;
    }

    public void execvip(Player p, String comando) {
        execvip = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(" §e§lVIPS");
                Bukkit.broadcastMessage(" §fO jogador §e" + p.getName() + " §fcom §e1% §fde chance ganhou um §eVIP Extra§f!");
                Bukkit.broadcastMessage("");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), comando);
                this.cancel();
            }
        }.runTaskTimer(getInstance(), 100, 100000);
    }

}
