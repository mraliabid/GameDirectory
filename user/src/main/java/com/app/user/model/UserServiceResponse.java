package com.app.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceResponse extends  BaseResponse{

    private List<User> gamers;

    public UserServiceResponse(Integer responseCode, String responseText, List<User> gamers) {
        super(responseCode, responseText);
        this.gamers = gamers;
    }

}
