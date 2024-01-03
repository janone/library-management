package org.example.controller;

import org.example.common.Result;
import org.example.entity.User;

/**
 * user crud
 */
public interface IUserController extends IController {

    /**
     * register user
     * @param user
     * @return
     */
    Result<Boolean> register(User user);

    /**
     * getUserByAccount
     * @param account
     * @return
     */
    Result<User> getUserByAccount(String account);


    /**
     * upgrade an account to admin
     * @param account
     * @return
     */
    Result<Boolean>  upgrade(String account);
}
