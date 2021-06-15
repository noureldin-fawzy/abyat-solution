package com.abyat.solution.model.payload;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
public class BasketballPlayer extends BasePlayer {
    private int points;
    private int rebounds;
    private int assists;

}
