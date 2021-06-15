package com.abyat.solution.service.impl;

import com.abyat.solution.service.Sport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportTypeFactory {
    public static Sport get(String type) {
        switch (type.toUpperCase()) {
            case "BASKETBALL":
                return new Basketball();
            case "HANDBALL":
                return new Handball();
        }
        return null;
    }
}
