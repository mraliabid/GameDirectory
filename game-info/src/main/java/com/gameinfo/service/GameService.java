package com.gameinfo.service;

import com.gameinfo.data.GameRepository;
import com.gameinfo.model.Game;
import com.gameinfo.model.GameServiceResponse;
import com.gameinfo.model.SingleGameResponse;
import com.gameinfo.util.CategoryEnum;
import com.gameinfo.util.GeographyEnum;
import com.gameinfo.util.LevelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public GameServiceResponse getGames(){
        GameServiceResponse gamesResponse = new GameServiceResponse();
        List<Game> gameResults = new ArrayList<>();
        gameRepository.findAll().forEach(gameResults::add);
        if(ObjectUtils.isEmpty(gameResults)){
            gamesResponse.setGamer(null);
            gamesResponse.setResponseCode(204);
            gamesResponse.setResponseText("Not Found");
        }else{
            gamesResponse.setGamer(gameResults);
            gamesResponse.setResponseCode(200);
            gamesResponse.setResponseText("Success");
        }
        return gamesResponse;
    }


    public SingleGameResponse getSingleGame(String id){
        Optional<Game> game = gameRepository.findById(id);
        Game gameResp = null;
        SingleGameResponse response = null;
        if(game != null || game.isPresent()) {

            gameResp = game.get();
            response = new SingleGameResponse(200, "Success", gameResp);
            return response;
        }
        response = new SingleGameResponse(204, "Not found", gameResp);
        return response;
    }

    public SingleGameResponse addGame(Game game){
        gameRepository.save(game);
        SingleGameResponse response = new SingleGameResponse(200, "User Added", game);
        return response;
    }

    public SingleGameResponse updateGame(String id, Game game) {
        gameRepository.save(game);
        SingleGameResponse response = new SingleGameResponse(200, "User Updated", game);
        return response;
    }

    public void deleteGame(String id) {gameRepository.deleteById(id);
    }


    public GameServiceResponse getFilterResult(String cat, String search) {
        List<Game> games = getGames().getGamer();
        List<Game> filterGames = Collections.emptyList();
        GameServiceResponse response = null;
        switch (cat) {
            case "interest":
                filterGames = getFilterCategory(search, games);
            case "geography":
                filterGames = getFilterGeography(search, games);
            default:
                filterGames = Collections.emptyList();
        }
        if(!ObjectUtils.isEmpty(filterGames)){
            response = new GameServiceResponse(200, "Filtered Results", filterGames);
        }else {
            response = new GameServiceResponse(204, "No Games Found", null);
        }
        return response;
    }



    public List<Game> getFilterCategory(String cat, List<Game> games) {
        List<Game> filterGames = Collections.emptyList();
        try {
            filterGames = games.stream().filter(game -> {
                return game.getInterest().equals(cat);
            }).collect(Collectors.toList());

        } catch (Exception e) {

        }
        return filterGames;
    }

    public List<Game> getFilterInterest(String level, List<Game> games) {
        List<Game> filterGames = Collections.emptyList();
        try {
            filterGames = games.stream().filter(game -> {
                return game.getLevel().equals(level);
            }).collect(Collectors.toList());

        } catch (Exception e) {

        }
        return filterGames;
    }


    public List<Game> getFilterGeography(String geo, List<Game> games) {
        List<Game> filterGames = Collections.emptyList();
        try {
            filterGames = games.stream().filter(game -> {
                return game.getGeography().equals(geo);
            }).collect(Collectors.toList());

        } catch (Exception e) {

        }
        return filterGames;
    }


    public GameServiceResponse getFilterGames(String interest, String geo) {
        List<Game> games = getGames().getGamer();
        List<Game> filterGames = Collections.emptyList();
        GameServiceResponse response = null;

        try {
            filterGames = games.stream().filter(game -> {
                return game.getGeography().equals(geo) || game.getInterest().equals(interest);
            }).collect(Collectors.toList());

        } catch (Exception e) {

        }

        if(!ObjectUtils.isEmpty(filterGames)){
            response = new GameServiceResponse(200, "Filtered Results", filterGames);
        }else {
            response = new GameServiceResponse(204, "No Games Found", null);
        }
        return response;
    }


    public void setUp() {
        List<Game> games = new ArrayList<>();
        games.add(new Game("1", "DOTA", CategoryEnum.Battle.getCategory(), LevelEnum.Noob.getLevel(), 0, GeographyEnum.SouthAmerica.getGeography()));
        games.add(new Game("2", "Call of Duty", CategoryEnum.Fight.getCategory(), LevelEnum.Pro.getLevel(), 0, GeographyEnum.NorthAmerica.getGeography()));
        games.add(new Game("3", "Fortnite", CategoryEnum.Fight.getCategory(), LevelEnum.Pro.getLevel(), 0, GeographyEnum.Asia.getGeography()));
        games.add(new Game("4", "Valhalla", CategoryEnum.Battle.getCategory(), LevelEnum.Pro.getLevel(), 0, GeographyEnum.Africa.getGeography()));
        games.add(new Game("5", "Amongus", CategoryEnum.Social.getCategory(), LevelEnum.Noob.getLevel(), 0, GeographyEnum.Antarctica.getGeography()));

        gameRepository.saveAll(games);
    }


}
