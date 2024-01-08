package org.example.controller.impl;

import org.example.annotation.AutoWiredField;
import org.example.common.BusinessException;
import org.example.common.Result;
import org.example.controller.IUserController;
import org.example.entity.User;
import org.example.service.UserService;

public class UserControllerImpl implements IUserController {
    @AutoWiredField
    private UserService userService;

    public Result<Boolean> register(User user){
        if(user.getAccount() == null || user.getAccount().trim().equals("")){
            throw new BusinessException("user account can not be empty");
        }
        if(user.getPassword()== null || user.getPassword().trim().equals("")){
            throw new BusinessException("password can not be empty");
        }
        if(user.getPassword().length() < 6){
            throw new BusinessException("password's length should not be less than 6");
        }

        if(user.getIsAdmin() == null){
            user.setIsAdmin(false);
        }

        userService.insert(user);

        return Result.success();
    }

    public Result<User> getUserByAccount(String account){
        User byId = userService.getById(account);
        return Result.successWithData(byId);
    }

    @Override
    public Result<Boolean> upgrade(String account) {

        if(account == null || account.trim().equals("")){
            return Result.fail("user account can not be empty");
        }

        if(userService.getById(account) == null){
            return Result.fail("user not exist");
        }

        User byId = userService.getById(account);
        if(byId.getIsAdmin()){
            throw new BusinessException("user is already admin");
        }

        byId.setIsAdmin(true);
        userService.update(byId);
        return Result.success();
    }


    public Result<String> login(String account, String password){
        String token = userService.login(account, password);
        return Result.successWithData(token);
    }


}
