package com.app.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleUserResponse extends  BaseResponse{

    private User gamers;

    public SingleUserResponse(Integer responseCode, String responseText, User gamers) {
        super(responseCode, responseText);
        this.gamers = gamers;
    }

}