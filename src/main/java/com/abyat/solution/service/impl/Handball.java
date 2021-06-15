package com.abyat.solution.service.impl;

import com.abyat.solution.model.HandballRatingPoints;
import com.abyat.solution.model.payload.BasePlayer;
import com.abyat.solution.model.payload.HandballPlayer;
import com.abyat.solution.repository.HandballRatingPointsDao;
import com.abyat.solution.service.Sport;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Handball extends SportAbstract implements Sport {

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
        HandballPlayer player = preparePlayerObject(playerStr);
        player.setTotalPoints(calculatePlayerPoints(player));
    }

    private int calculatePlayerPoints(HandballPlayer player) {
        HandballRatingPoints ratingPoints = HandballRatingPointsDao.getRatingPoints(player.getPosition());
        int playerTotalPoints = (ratingPoints.getInitialPoints()) + (player.getGoalsMade() * ratingPoints.getGoalMade()) + (player.getGoalsReceived() * ratingPoints.getGoalReceived());
        log.info("Player {}, points {}", player.getNickname(), playerTotalPoints);
        handleTeamsMap(player, playerTotalPoints);
        return playerTotalPoints;
    }

    private HandballPlayer preparePlayerObject(String playerStr) {
        String[] split = playerStr.split(";");

        return HandballPlayer.builder()
                .name(split[0])
                .nickname(split[1])
                .number(Integer.valueOf(split[2]))
                .teamName(split[3])
                .position(split[4].charAt(0))
                .goalsMade(Integer.valueOf(split[5]))
                .goalsReceived(Integer.valueOf(split[6]))
                .build();
    }
}