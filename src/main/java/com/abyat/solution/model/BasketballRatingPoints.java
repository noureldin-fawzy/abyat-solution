package com.abyat.solution.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
//@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketballRatingPoints {

//    @Id
//    private int id;
    private char position;
    private int points;
    private int rebound;
    private int assist;

}
