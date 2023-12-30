package org.example.controller;

import org.example.common.Result;
import org.example.entity.User;
import org.example.service.UserService;

public interface UserController extends Controller{

    Result<Boolean> register(User user);
    Result<User> getUserByAccount(String account);



}
