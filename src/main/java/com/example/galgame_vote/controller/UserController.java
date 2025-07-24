package com.example.galgame_vote.controller;


import com.example.galgame_vote.pojo.User;
import com.example.galgame_vote.pojo.util.ResponseMessage;
import com.example.galgame_vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //登录
    @RequestMapping("/login")
    public ResponseMessage<Boolean> login(@RequestBody User user) {
        return userService.login(user.getAccount());
    }


}
