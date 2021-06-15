package com.abyat.solution.model.payload;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BasePlayer {
    private String name;
    private String nickname;
    private int number;
    private String teamName;
    private char position;
    private int totalPoints;
}
