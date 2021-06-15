package com.abyat.solution.repository;

import com.abyat.solution.model.BasketballRatingPoints;

import java.util.ArrayList;
import java.util.List;

public class BasketballRatingPointsDao {

    private static List<BasketballRatingPoints> points = new ArrayList<>();

    static {
        points.add(BasketballRatingPoints.builder().position('G').points(2).rebound(3).assist(1).build());
        points.add(BasketballRatingPoints.builder().position('F').points(2).rebound(2).assist(2).build());
        points.add(BasketballRatingPoints.builder().position('C').points(2).rebound(1).assist(3).build());
    }

    public static BasketballRatingPoints getRatingPoints(char position) {
        return points.stream().filter(points -> points.getPosition() == position).findFirst().get();
    }

}
