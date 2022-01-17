package com.app.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {

    private String id;
    private String name;
    private Integer age;
    private String nickName;
    private String geography;
    private String level;
    private Integer score;
    private List<String> interest;
    private List<Game> gamesEnrolled;

    public User(String id, String name, Integer age, String nickName, String geography, String level, Integer score, List<String> interest, List<Game> gamesEnrolled) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nickName = nickName;
        this.geography = geography;
        this.level = level;
        this.score = score;
        this.interest = interest;
        this.gamesEnrolled = gamesEnrolled;
    }

}
