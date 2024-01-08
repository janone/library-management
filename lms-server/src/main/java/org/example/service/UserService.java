package org.example.service;

import org.example.common.BusinessException;
import org.example.common.MD5Util;
import org.example.dao.UserDao;
import org.example.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * user crud service
 */
public class UserService extends BaseService<UserDao, User>{

    private Map<String,User> loginUserMap = new HashMap<>();

    /**
     * if login success, return token
     * @param account
     * @param password
     * @return
     */
    public String login(String account, String password){

        User byId = getById(account);

        if(byId == null){
            throw new BusinessException("account does not exist : " + account);
        }

        if(!byId.getPassword().equals(password)){
            throw new BusinessException("password is not correct for account : " + account);
        }
        String md5 = MD5Util.getMD5(System.currentTimeMillis() + account);
        loginUserMap.put(md5, byId);
        return md5;
    }

    public boolean isLogin(String token) {
        return loginUserMap.containsKey(token);
    }
}
