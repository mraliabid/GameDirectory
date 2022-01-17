package com.app.user.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {

    private String id;
    private String name;
    private String interest;
    private String level;
    private Integer score;
    private String geography;

}


