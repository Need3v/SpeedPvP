package br.com.speedmc.vote;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import static br.com.speedmc.SpeedPvP.*;

public class VoteParty {

    int sec = 30;
    int votes;
    private BukkitTask announce;

    public void setupVotes() {
        votes = getVotePartyFile().getConfig().getInt("Votos");
        Bukkit.getConsoleSender().sendMessage("§b§l[Votos] §fVotos carregados: " + votes);
        announce();
    }

    public void addVote() {
        ++votes;
        Bukkit.getConsoleSender().sendMessage("§b§l[Votos] §fVote party atual: " + votes);
        if (votes == 60) {
            startDrop();
            votes = 0;
        }
    }

    public void saveVotes() {
        getVotePartyFile().getConfig().set("Votos", votes);
        getVotePartyFile().saveConfig();
        Bukkit.getConsoleSender().sendMessage("§b§l[Votos] §fVotos salvados: " + votes);
    }

    public void announce() {
        announce = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(" §e§lVOTOS §7(/vote)");
                Bukkit.broadcastMessage(" §fVote no servidor e ganhe recompensas!");
                Bukkit.broadcastMessage(" §fComplete a Vote Party e ganhe uma Caixa Lendária.");
                Bukkit.broadcastMessage(" §fVote Party atual: §b" +votes+"/60");
                Bukkit.broadcastMessage("");
            }
        }.runTaskTimerAsynchronously(getInstance(), 21620, 21620);
    }

    public int getVotes() {return votes;}


    // DROP SYSTEM

    public void startDrop(){
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(" §d§lVote Party ➜ §a§lCompletada!");
        Bukkit.broadcastMessage(" §fTodos online receberam uma §6Caixa Lendária§f!");
        Bukkit.broadcastMessage("");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mbox giveall lendaria 1");
    }

}
