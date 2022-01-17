package com.gamedirectory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GameDirResponse extends  BaseResponse{

    List<TopGamers> gamersList;

    public GameDirResponse(Integer responseCode, String responseText, List<TopGamers> gamersList) {
        super(responseCode, responseText);
        this.gamersList = gamersList;
    }

    public GameDirResponse(List<TopGamers> gamersList) {
        this.gamersList = gamersList;
    }
}
