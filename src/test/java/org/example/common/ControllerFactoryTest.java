package org.example.common;

import org.example.controller.UserController;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerFactoryTest {

    private ControllerFactory factory = new ControllerFactory();

    @BeforeEach
    void setUp() throws Exception {
        factory.register(new UserControllerImpl());
        factory.init();
    }
    @Test
    void getBean() {
        UserController bean = factory.getBean(UserController.class);
        User user = new User();
        user.setAccount("Jack");
        user.setPassword("1234345");
        user.setAdmin(true);
        bean.register(user);

        Result<User> jack = bean.getUserByAccount("Jack");
        User data = jack.getData();
        System.out.println(data);
    }

    @Test
    void register() {
    }
}