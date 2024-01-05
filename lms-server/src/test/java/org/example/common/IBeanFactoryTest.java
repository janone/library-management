package org.example.common;

import org.example.controller.IUserController;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IBeanFactoryTest {


    BeanFactory factory;
    @BeforeEach
    void setUp() throws Exception {
    }
    @Test
    void getBean() {
        IUserController bean = BeanFactory.getBean(UserControllerImpl.class);
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

    @Test
    void upgrade() {
        IUserController bean = BeanFactory.getBean(UserControllerImpl.class);
        User user = new User();
        user.setAccount("Jack");
        user.setPassword("1234345");
        user.setIsAdmin(false);
        bean.register(user);

        bean.upgrade("Jack");
        Result<User> userByAccount = bean.getUserByAccount("Jack");


        Assertions.assertTrue(userByAccount.isSuccess() && userByAccount.getData().getIsAdmin());
    }
}