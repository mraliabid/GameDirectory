package com.gamedirectory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserServiceResponse extends  BaseResponse{

    private List<Gamer> gamers;

    public UserServiceResponse(Integer responseCode, String responseText) {
        super(responseCode, responseText);
    }


    public UserServiceResponse(Integer responseCode, String responseText, List<Gamer> gamers) {
        super(responseCode, responseText);
        this.gamers = gamers;
    }

}
