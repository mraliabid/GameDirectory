package com.gameinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleGameResponse extends BaseResponse {

    private Game game;

    public SingleGameResponse(Integer responseCode, String responseText, Game game) {
        super(responseCode, responseText);
        this.game = game;
    }
}
