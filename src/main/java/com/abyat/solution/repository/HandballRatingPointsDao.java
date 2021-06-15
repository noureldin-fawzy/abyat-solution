package com.abyat.solution.repository;

import com.abyat.solution.model.HandballRatingPoints;

import java.util.ArrayList;
import java.util.List;

public class HandballRatingPointsDao {

    private static List<HandballRatingPoints> points = new ArrayList<>();

    static {
        points.add(HandballRatingPoints.builder().position('G').initialPoints(50).goalMade(5).goalReceived(-2).build());
        points.add(HandballRatingPoints.builder().position('F').initialPoints(20).goalMade(1).goalReceived(-1).build());
    }

    public static HandballRatingPoints getRatingPoints(char position) {
        return points.stream().filter(points -> points.getPosition() == position).findFirst().get();
    }

}
