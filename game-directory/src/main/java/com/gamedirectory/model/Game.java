package com.gamedirectory.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    private String id;
    private String name;
    private String interest;
    private String level;
    private Integer score;
    private String geography;

}

