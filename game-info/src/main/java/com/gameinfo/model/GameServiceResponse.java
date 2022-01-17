package com.gameinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameServiceResponse extends  BaseResponse{

    private List<Game> gamer;

    public GameServiceResponse(Integer responseCode, String responseText, List<Game> gamer) {
        super(responseCode, responseText);
        this.gamer = gamer;
    }

}
