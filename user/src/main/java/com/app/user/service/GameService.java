package com.app.user.service;

import com.app.user.model.Game;
import com.app.user.model.GameServiceResponse;
import com.app.user.model.SingleGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    RestMessagingSvc restMessagingSvc;


    public Game getGameDetails(String gameId){

        SingleGameResponse response = restMessagingSvc.sendGetRequest("http://localhost:8081/game/"+gameId, SingleGameResponse.class);
        if(response != null && response.getGame() != null){
            return response.getGame();
        }
        return null;


    }

    public GameServiceResponse getAllGames() {
        GameServiceResponse response = restMessagingSvc.sendGetRequest("http://localhost:8081/game", GameServiceResponse.class);
        return response;
    }

}
