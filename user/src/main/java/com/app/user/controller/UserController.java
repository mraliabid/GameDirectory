package com.app.user.controller;

import com.app.user.model.BaseResponse;
import com.app.user.model.SingleUserResponse;
import com.app.user.model.User;
import com.app.user.model.UserServiceResponse;
import com.app.user.service.UserService;
import com.app.user.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/setUp")
    public void setUsers(){
        userService.initializeUsers();
    }

    @RequestMapping("/user")
    public ResponseEntity<UserServiceResponse> getUsers(){
        UserServiceResponse resp = userService.getUser();
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }


    @RequestMapping("/user/{id}")
    public ResponseEntity<SingleUserResponse> getUser(@PathVariable String id){
        SingleUserResponse resp = userService.getSingleUser(id);
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }


    @PostMapping("/addUser")
    public ResponseEntity<BaseResponse> getUser(@RequestBody User Gamer){
        BaseResponse resp = userService.addUser(Gamer);
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<SingleUserResponse> updateUser(@RequestBody User Gamer, @PathVariable String id){
        SingleUserResponse resp = userService.updateUser(id, Gamer);;
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return null;
    }


    @PutMapping("/user/{id}/enrollGame/{gameId}")
    public ResponseEntity<UserServiceResponse> enrolGame(@PathVariable String id, @PathVariable String gameId){
        UserServiceResponse resp =userService.gameEnrolled(id, gameId);
        return CommonUtil.getResponseEntity(resp.getResponseCode(), resp);
    }
}
