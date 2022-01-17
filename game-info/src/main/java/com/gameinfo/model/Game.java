package com.gameinfo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Game {

    @Id
    private String id;
    private String name;
    private String interest;
    private String level;
    private Integer score;
    private String geography;

    public Game(String id, String name, String interest, String level) {
        this.id = id;
        this.name = name;
        this.interest = interest;
        this.level = level;
    }

    public Game(String id, String name, String interest, String level, Integer score, String geography) {
        this.id = id;
        this.name = name;
        this.interest = interest;
        this.level = level;
        this.score = score;
        this.geography = geography;
    }

}

