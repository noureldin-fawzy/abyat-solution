package com.abyat.solution.service.impl;

import com.abyat.solution.model.BasketballRatingPoints;
import com.abyat.solution.model.payload.BasketballPlayer;
import com.abyat.solution.repository.BasketballRatingPointsDao;
import com.abyat.solution.service.Sport;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Basketball extends SportAbstract implements Sport {


    @Override
    public Map<String, Integer> process(List<String> match) {
        List<String> playersStr = getPlayers(match);
        handlePlayersPoints(playersStr);
        handleWinnerTeam();
        return extractPlayersFromTeams();
    }

    private void handlePlayersPoints(List<String> players) {
        players.forEach(this::processPlayer);
    }

    private void processPlayer(String playerStr) {
        BasketballPlayer player = preparePlayerObject(playerStr);
        player.setTotalPoints(calculatePlayerPoints(player));
    }

    private int calculatePlayerPoints(BasketballPlayer player) {
        BasketballRatingPoints ratingPoints = BasketballRatingPointsDao.getRatingPoints(player.getPosition());
        int playerTotalPoints = (player.getPoints() * ratingPoints.getPoints()) + (player.getRebounds() * ratingPoints.getRebound()) + (player.getAssists() * ratingPoints.getAssist());
        log.info("Player {}, points {}", player.getNickname(), playerTotalPoints);
        handleTeamsMap(player, playerTotalPoints);
        return playerTotalPoints;
    }

    private void handleTeamsMap(BasketballPlayer player, int playerTotalPoints) {
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

    private BasketballPlayer preparePlayerObject(String playerStr) {
        String[] split = playerStr.split(";");

        return BasketballPlayer.builder()
                .name(split[0])
                .nickname(split[1])
                .number(Integer.valueOf(split[2]))
                .teamName(split[3])
                .position(split[4].charAt(0))
                .points(Integer.valueOf(split[5]))
                .rebounds(Integer.valueOf(split[6]))
                .assists(Integer.valueOf(split[7]))
                .build();
    }
}
