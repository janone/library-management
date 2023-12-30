package org.example.controller.impl;

import org.example.common.Result;
import org.example.controller.UserController;
import org.example.entity.User;
import org.example.service.UserService;

public class UserControllerImpl implements UserController {

    private UserService userService = new UserService();

    public Result<Boolean> register(User user){
        if(user.getAccount() == null || user.getAccount().equals("")){
            return Result.fail("user account can not be empty");
        }
        if(user.getPassword()== null || user.getPassword().equals("")){
            return Result.fail("password can not be empty");
        }

        userService.insert(user);

        return Result.success();
    }

    public Result<User> getUserByAccount(String account){
        User byId = userService.getById(account);
        return Result.successWithData(byId);
    }

}
