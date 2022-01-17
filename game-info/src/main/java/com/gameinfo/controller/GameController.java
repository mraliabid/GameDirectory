package com.gameinfo.controller;

import com.gameinfo.model.Game;
import com.gameinfo.model.GameServiceResponse;
import com.gameinfo.model.SingleGameResponse;
import com.gameinfo.service.GameService;
import com.gameinfo.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    GameService userService;


    @GetMapping("/setup")
    public void setUpGames(){
        userService.setUp();
    }

    @GetMapping("/game")
    public ResponseEntity<GameServiceResponse> getGames(){
        GameServiceResponse response = userService.getGames();
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<SingleGameResponse> getGame(@PathVariable String id){

        SingleGameResponse response = userService.getSingleGame(id);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    @PostMapping("/addGame")
    public ResponseEntity<SingleGameResponse> addGame(@RequestBody Game Game){
        SingleGameResponse response = userService.addGame(Game);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    @PutMapping("/updateGame/{id}")
    public ResponseEntity<SingleGameResponse> updateGame(@RequestBody Game game, @PathVariable String id){
        SingleGameResponse response = userService.updateGame(id, game);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    @DeleteMapping("/deleteGame/{id}")
    public Void deleteGame(@PathVariable String id){
        userService.deleteGame(id);
        return null;
    }

    @GetMapping("/interest/{interest}")
    public ResponseEntity<GameServiceResponse> getGameInterest(@PathVariable String interest){
        GameServiceResponse response =userService.getFilterResult("category", interest);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    @GetMapping("/geo/{geo}")
    public ResponseEntity<GameServiceResponse> getGameGeography(@PathVariable String geo){
        GameServiceResponse response =userService.getFilterResult("geography", geo);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }


    @GetMapping("/interest/{interest}/geo/{geo}")
    public ResponseEntity<GameServiceResponse> getFilterGames(@PathVariable String interest, @PathVariable String geo){
        GameServiceResponse response =userService.getFilterGames(interest, geo);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

}
