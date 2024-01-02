package org.example.view;

import org.example.common.ControllerFactory;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginRegisterViewTest {

    @BeforeEach
    void setUp(){
    }

    @Test
    void show() {

        User show = new UserLoginRegisterView().show();
        System.out.println(show);

    }
}