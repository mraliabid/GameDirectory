package com.gamedirectory.model;

import com.gamedirectory.model.validator.RequestValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequestValidator
public class Gamer {

    private String id;
    private String name;
    private Integer age;
    private String nickName;
    private String geography;
    private String level;
    private Integer score;
    private List<String> interest;
    private List<Game> gamesEnrolled;

}
