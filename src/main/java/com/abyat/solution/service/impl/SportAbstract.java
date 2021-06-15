package com.abyat.solution.service.impl;

import com.abyat.solution.model.payload.BasePlayer;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SportAbstract {

    protected Map<String, Map<String, Integer>> teams = new HashMap<>();

    protected List<String> getPlayers(List<String> match) {
        return match.subList(1, match.size());
    }

    protected Map<String, Integer> extractPlayersFromTeams() {
        Map<String, Integer> players = new HashMap<>();
        teams.forEach((k, v) -> v.forEach((name, points) -> players.put(name, points)));
        return players;
    }

    protected void handleTeamsMap(BasePlayer player, int playerTotalPoints) {
        if (teams.containsKey(player.getTeamName())) {
            Map<String, Integer> teamMembers = teams.get(player.getTeamName());

            if (teamMembers.containsKey(player.getNickname())) {
                Integer oldPoints = teamMembers.get(player.getNickname());
                teamMembers.replace(player.getNickname(), oldPoints + playerTotalPoints);
            } else {
                teamMembers.put(player.getNickname(), playerTotalPoints);
            }

        } else {
            Map<String, Integer> member = new HashMap<>();
            member.put(player.getNickname(), playerTotalPoints);
            teams.put(player.getTeamName(), member);
        }
    }

    protected void handleWinnerTeam() {
        Map<String, Integer> teamsPoints = new HashMap<>();
        teams.forEach((k, v) -> {
            teamsPoints.put(k, v.values().stream().mapToInt(i -> i).sum());
        });

        String winnerTeam = Collections.max(teamsPoints.entrySet(), Map.Entry.comparingByValue()).getKey();
        log.info("The winner is: {}", winnerTeam);

        Map<String, Integer> winnerPlayers = teams.get(winnerTeam);
        winnerPlayers.forEach((name, points) -> {
            winnerPlayers.replace(name, points, points + 10);
        });
    }

}
