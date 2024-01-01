package org.example.controller.impl;

import org.example.common.Result;
import org.example.controller.IUserController;
import org.example.entity.User;
import org.example.service.UserService;

public class UserControllerImpl implements IUserController {

    private UserService userService = new UserService();

    public Result<Boolean> register(User user){
        if(user.getAccount() == null || user.getAccount().trim().equals("")){
            return Result.fail("user account can not be empty");
        }
        if(user.getPassword()== null || user.getPassword().trim().equals("")){
            return Result.fail("password can not be empty");
        }
        if(user.getPassword().length() < 6){
            return Result.fail("password's length should not be less than 6");
        }

        userService.insert(user);

        return Result.success();
    }

    public Result<User> getUserByAccount(String account){
        User byId = userService.getById(account);
        return Result.successWithData(byId);
    }


}
