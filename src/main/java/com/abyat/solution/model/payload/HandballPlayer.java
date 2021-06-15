package com.abyat.solution.model.payload;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HandballPlayer extends BasePlayer {
    private int goalsMade;
    private int goalsReceived;
}
