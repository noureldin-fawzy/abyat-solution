package com.abyat.solution.service.impl;

import com.abyat.solution.model.payload.request.MatchFileRequest;
import com.abyat.solution.service.MatchService;
import com.abyat.solution.service.Sport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private Map<String, Integer> tournamentPlayers = new HashMap<>();

    @Override
    public String process(MatchFileRequest request) {
        processTournament(request);
        String mvp = getMVP();
        log.info("MVP is: {}", mvp);
        return "MVP is: " + mvp;
    }

    private void processTournament(MatchFileRequest request) {
        request.getFiles().forEach(this::handleMatch);
    }

    private void handleMatch(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<String> stringList = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.toList());

            String type = stringList.get(0);
            log.info("Match type: {}", type);

            Sport sport = SportTypeFactory.get(type);
            Map<String, Integer> players = sport.process(stringList);
            updateTournamentPlayersMap(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getMVP() {
        return Collections.max(tournamentPlayers.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private void updateTournamentPlayersMap(Map<String, Integer> players) {
        players.forEach((name, points) -> {

            if (tournamentPlayers.containsKey(name)) {
                tournamentPlayers.replace(name, tournamentPlayers.get(name) + points);
            } else {
                tournamentPlayers.put(name, points);
            }

        });
    }

}
