package com.gamedirectory.controller;

import com.gamedirectory.model.*;
import com.gamedirectory.service.GameDirService;
import com.gamedirectory.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DirectoryController {

    @Autowired
    GameDirService gameDirService;

    /**
     * endpoint to get all gamers in the game dir
     * @return
     */
    @GetMapping("/getGamers")
    public ResponseEntity<UserServiceResponse> getUsers(){
        // get details from game dir
        UserServiceResponse response = gameDirService.getUsersInfo();
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    /**
     * endpoint to add gamer to game dir with request validation from custom validator
     * @param gamer
     * @param error
     * @return
     */
    @PostMapping("/addGamer")
    public ResponseEntity<BaseResponse> addNewUsers(@Valid @RequestBody Gamer gamer, Errors error){
        // check valid request to have no errors
        if(error.hasErrors()){
            // return response as request is not valid
            return CommonUtil.getResponseEntity(401,null);
        }
        // add user
        BaseResponse resp = gameDirService.addUser(gamer);
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }

    /**
     * service to enroll gamer to the gameId he selected
     * @param id
     * @param gameId
     * @return
     */
    @PutMapping("/gamer/{id}/enrollGame/{gameId}")
    public ResponseEntity<UserServiceResponse> enrollGame(@PathVariable String id, @PathVariable String gameId){
        UserServiceResponse resp = gameDirService.gameEnrolled(id, gameId);
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }

    /**
     * endpoint to get all top game scorers
     * @return
     */
    @GetMapping("/topGames")
    public ResponseEntity<GameDirResponse> getTopGamers(){
        GameDirResponse response = gameDirService.getTopGamesScorer();
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    /**
     * get the games on the basis of gamer interest
     * @param interest
     * @return
     */
    @GetMapping("/interest/{interest}")
    public ResponseEntity<GameServiceResponse> getGameInterest(@PathVariable String interest){
        GameServiceResponse response =gameDirService.getGameInterestResult( interest);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    /**
     * get games on the basis of geo location
     * @param geo
     * @return
     */
    @GetMapping("/geo/{geo}")
    public ResponseEntity<GameServiceResponse> getGameGeography(@PathVariable String geo){
        GameServiceResponse response =gameDirService.getGameGeoResult(geo);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

    /**
     * get games on the basis of gamers interest and geo
     * @param interest
     * @param geo
     * @return
     */
    @GetMapping("/interest/{interest}/geo/{geo}")
    public ResponseEntity<GameServiceResponse> getFilterGames(@PathVariable String interest, @PathVariable String geo){
        GameServiceResponse response =gameDirService.getFilterGames(interest, geo);
        return CommonUtil.getResponseEntity(response.getResponseCode(), response);
    }

}
