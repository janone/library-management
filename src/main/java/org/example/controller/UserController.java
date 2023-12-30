package org.example.controller;

import org.example.common.Result;
import org.example.entity.User;
import org.example.service.UserService;

public class UserController {

    private UserService userService = new UserService();

    public Result<Boolean> register(User user){
        if(user.getAccount() == null || user.getAccount().equals("")){
            return Result.fail("user account can not be empty");
        }
        if(user.getPassword()== null || user.getPassword().equals("")){
            return Result.fail("password can not be empty");
        }

        return Result.success();
    }

}
