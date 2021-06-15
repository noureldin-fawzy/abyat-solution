package com.abyat.solution.controller;

import com.abyat.solution.model.payload.request.MatchFileRequest;
import com.abyat.solution.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @PostMapping()
    public String match(@ModelAttribute MatchFileRequest request) {
        return matchService.process(request);
    }
}
