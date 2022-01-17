package com.gamedirectory.service;

import com.gamedirectory.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class GameDirService {

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RestMessagingSvc restMessagingSvc;

    public UserServiceResponse getUsersInfo() {

        UserServiceResponse response = null;
        try {
            response = restMessagingSvc.sendGetRequest("http://localhost:8082/user", UserServiceResponse.class);
        }catch (Exception e){
            response = new UserServiceResponse(500, "INTERNAL SERVER ERROR");
        }
        return response;
    }


    public BaseResponse addUser( Gamer gamer) {
        BaseResponse response = restMessagingSvc.sendPostRequest(gamer, "http://localhost:8082/addUser", BaseResponse.class);
        return response;
    }

    public GameServiceResponse getAllGames() {
        GameServiceResponse response = restMessagingSvc.sendGetRequest("http://localhost:8081/game", GameServiceResponse.class);
        return response;
    }


    public BaseResponse addGame( Game game) {
        BaseResponse response = restMessagingSvc.sendPostRequest(game, "http://localhost:8081/addGame", BaseResponse.class);
        return response;
    }

    public UserServiceResponse gameEnrolled(String userId, String gameId){
        UserServiceResponse response = restMessagingSvc.sendPutRequest("null","http://localhost:8082/user/"+userId+"/enrollGame/"+gameId, UserServiceResponse.class);
        return response;
    }



    public GameDirResponse getTopGamesScorer() {
        UserServiceResponse gamers = restMessagingSvc.sendGetRequest("http://localhost:8082/user", UserServiceResponse.class);
        GameServiceResponse gameResponse = restMessagingSvc.sendGetRequest("http://localhost:8081/game", GameServiceResponse.class);


        ArrayList<TopGamers> topGamers = new ArrayList();
        GameDirResponse response = null;

        try {
            for (Gamer gamer : gamers.getGamers()) {
                for (Game game : gamer.getGamesEnrolled()) {

                    TopGamers filterGame = topGamers.stream().filter(g -> g.getGameId().equals(game.getId()))
                            .findAny().orElse(null);

                    if (filterGame != null && filterGame.getScore() <= game.getScore()) {
                        filterGame.setScore(game.getScore());
                        filterGame.setGamerName(gamer.getName());
                        filterGame.setGamerId(gamer.getId());
                        filterGame.setGeography(gamer.getGeography());

                        // update game
                        for (int i = 0; i < topGamers.size(); i++) {
                            TopGamers g = topGamers.get(i);
                            if (g.getGameId().equals(filterGame.getGameId())) {
                                topGamers.set(i, filterGame);
                            }
                        }
                    } else {
                        TopGamers topGamer = new TopGamers(game.getId(),
                                game.getName(),
                                gamer.getName(),
                                gamer.getId(),
                                game.getInterest(),
                                game.getLevel(),
                                game.getScore(),
                                game.getGeography());
                        topGamers.add(topGamer);
                    }
                }
            }
        } catch (Exception e) {
            response = new GameDirResponse(500, "Internal Server Error", null);
        }
        if (!ObjectUtils.isEmpty(topGamers)) {
            response = new GameDirResponse(200, "Success", topGamers);
        } else {
            response = new GameDirResponse(404, "No Record Found", null);
        }

        return response;
    }


    public GameServiceResponse getGameInterestResult(String search) {

        GameServiceResponse response = restMessagingSvc.sendGetRequest("http://localhost:8081/interest/"+search, GameServiceResponse.class);
        return response;

    }

    public GameServiceResponse getGameGeoResult(String search) {

        GameServiceResponse response = restMessagingSvc.sendGetRequest("http://localhost:8081/geo/"+search, GameServiceResponse.class);
        return response;

    }




    public GameServiceResponse getFilterGames(String interest, String geo) {
        GameServiceResponse response = null;

        try {
            response = restMessagingSvc.sendGetRequest("http://localhost:8081/interest/"+interest+"/geo/"+geo, GameServiceResponse.class);
        } catch (Exception e) {

        }

        if(!ObjectUtils.isEmpty(response.getGamer())){
            return  response;
        }
        return response;
    }

}
