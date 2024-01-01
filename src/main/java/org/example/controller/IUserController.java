package org.example.controller;

import org.example.common.Result;
import org.example.entity.User;

public interface IUserController extends IController {

    Result<Boolean> register(User user);
    Result<User> getUserByAccount(String account);



}
