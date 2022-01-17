package com.gamedirectory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopGamers {

    private String gameId;
    private String gameName;
    private String gamerId;
    private String gamerName;
    private String interest;
    private String level;
    private Integer score;
    private String geography;

    public TopGamers(String gameId, String gameName, String gamerId, String gamerName, String interest, String level, Integer score, String geography) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gamerId = gamerId;
        this.gamerName = gamerName;
        this.interest = interest;
        this.level = level;
        this.score = score;
        this.geography = geography;
    }

}
