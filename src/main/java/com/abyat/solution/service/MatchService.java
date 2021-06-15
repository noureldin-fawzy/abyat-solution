package com.abyat.solution.service;

import com.abyat.solution.model.payload.request.MatchFileRequest;

public interface MatchService {
    String process(MatchFileRequest request);
}
