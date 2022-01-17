package com.app.user.service;

import com.app.user.model.*;
import com.app.user.util.CategoryEnum;
import com.app.user.util.GeographyEnum;
import com.app.user.util.LevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    GameService gameService;

    List<String> interests = Arrays.asList();

    ArrayList<User> gamers =  new ArrayList<User>();

    public UserServiceResponse getUser(){
        UserServiceResponse response = new UserServiceResponse(200, "Success", gamers);
        return response;
    }


    public SingleUserResponse getSingleUser(String id){

        SingleUserResponse response = new SingleUserResponse();

        User gamer = gamers.stream().filter(g -> g.getId().equals(id)).findFirst().get();
        if(!ObjectUtils.isEmpty(gamer)){
            response.setGamers(gamer);
            response.setResponseCode(200);
            response.setResponseText("Gamer Found");
        }else{
            response.setResponseCode(500);
            response.setResponseText("User Not Found");
        }
        return response;
    }



    public BaseResponse addUser(User Gamer){
        gamers.add(Gamer);
        return  new BaseResponse(200,"UserAdded");
    }

    public SingleUserResponse updateUser(String id, User gamer) {

        SingleUserResponse response = new SingleUserResponse();
        boolean updated = false;

        for (int i=0; i<gamers.size(); i++) {
            User g = gamers.get(i);
            if(g.getId().equals(id)){
                gamers.set(i, gamer);
                updated = true;
                response.setResponseCode(200);
                response.setResponseText("Gamer Updated");
                response.setGamers(gamer);
                return response;
            }
        }
        response.setResponseCode(204);
        response.setResponseText("Gamer Not Updated");
        return  response;

    }

    public void deleteUser(String id) {gamers.removeIf(g -> g.getId().equals(id));
    }


    public UserServiceResponse gameEnrolled(String userId, String gameId){
        User gamer = gamers.stream().filter(g -> g.getId().equals(userId)).findFirst().get();

        Game game = gameService.getGameDetails(gameId);

        if(game != null){
            // set game score as of user level
            game.setScore(LevelEnum.of(gamer.getLevel()).getCredit());
            if(gamer.getGamesEnrolled() != null) {
                gamer.getGamesEnrolled().add(game);
            }else{
                ArrayList<Game> games = new ArrayList<>();
                games.add(game);
                gamer.setGamesEnrolled(games);
            }
            // update gamer score
            Integer credit = gamer.getScore();
            credit += game.getScore();
            gamer.setScore(credit);
        }
        updateUser(userId, gamer);

        ArrayList gamers = new ArrayList();
        gamers.add(gamer);

        UserServiceResponse response = new UserServiceResponse(200, "Success", gamers);
        return  response;
    }

    /**
     * method to auto-Calculate the credit of the gamer on the basis of the games level they are enrolled
     */
    public void giveCredit(){
        gamers = (ArrayList<User>) gamers.stream().map(gamer -> {
            AtomicInteger credit = new AtomicInteger();
            for (Game game: gamer.getGamesEnrolled()) {
                credit.addAndGet(LevelEnum.of(game.getLevel()).getCredit());
            }
            gamer.setScore(credit.get());
            return gamer;
        }).collect(Collectors.toList());
    }

    public void initializeUsers() {
        gamers = new ArrayList<User>();
            List<String> interest = new ArrayList<>();
            interest.add(CategoryEnum.Battle.getCategory());
            interest.add(CategoryEnum.Fight.getCategory());

        gamers = new ArrayList<>(Arrays.asList(
                    new User("1", "Ai", 23, "Daredevil", GeographyEnum.Asia.getGeography(), LevelEnum.Noob.getLevel(),0, interest, null),
                    new User("2", "Mudassir", 23, "KungFu Master", GeographyEnum.NorthAmerica.getGeography(), LevelEnum.Pro.getLevel(), 0, interest, null),
                    new User("3", "Jeppe", 20, "Hawkeye", GeographyEnum.Antarctica.getGeography(), LevelEnum.Pro.getLevel(), 0, interest, null)
            ));
        }

}
