package org.example.common;

import org.example.controller.IUserController;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IControllerFactoryTest {


    ControllerFactory factory;
    @BeforeEach
    void setUp() throws Exception {
        factory = ControllerFactory.getInstance();
        factory.registerToBeanFactory(new UserControllerImpl());
    }
    @Test
    void getBean() {
        IUserController bean = factory.getBean(IUserController.class);
        User user = new User();
        user.setAccount("Jack");
        user.setPassword("1234345");
        user.setIsAdmin(true);
        bean.register(user);

        Result<User> jack = bean.getUserByAccount("Jack");
        User data = jack.getData();
        System.out.println(data);
    }

    @Test
    void register() {
    }
}