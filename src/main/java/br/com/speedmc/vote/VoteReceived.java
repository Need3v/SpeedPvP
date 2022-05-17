package br.com.speedmc.vote;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import static br.com.speedmc.SpeedPvP.*;

public class VoteReceived implements Listener {

    @EventHandler
    public void onVotifierEvent(VotifierEvent event) {
        Vote vote = event.getVote();
        getVoteReward().giveReward(vote.getUsername(), vote.getServiceName());
        getVoteParty().addVote();

    }
}
