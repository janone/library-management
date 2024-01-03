package org.example;

import org.example.common.BeanFactory;
import org.example.common.responsibilitychain.LogChain;
import org.example.controller.IBookController;
import org.example.controller.IUserController;
import org.example.controller.impl.BookControllerImpl;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.User;
import org.example.view.MainView;

import java.util.Scanner;

/**
 * @Author: Zeng Jian
 * @DateTime: 2023-12-19 14:58
 * @Description: Library Management	System Entrance
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);


    public static Scanner getScanner(){
        return scanner;
    }
    public static void main(String[] args) {

        IUserController userController = BeanFactory.getBean(UserControllerImpl.class);
        User user = new User();
        user.setAccount("root");
        user.setPassword("root11");
        user.setIsAdmin(true);
        userController.register(user);

        User user2 = new User();
        user2.setAccount("user1");
        user2.setPassword("user11");
        userController.register(user2);

        IBookController bookController = BeanFactory.getBean(BookControllerImpl.class);
        for (int i = 0; i < 5; i++) {
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("author"+i);
            bookItem.setInventory(i+1);
            bookController.addBook(bookItem);

        }

        // initial chain
        BeanFactory.getBean(LogChain.class);


        new MainView().show(null);




    }
}