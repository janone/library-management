package org.example;

import org.example.common.ControllerFactory;
import org.example.controller.IBookController;
import org.example.controller.IUserController;
import org.example.controller.impl.BookControllerImpl;
import org.example.controller.impl.BorrowRecordControllerImpl;
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

        IUserController userController = ControllerFactory.getBean(UserControllerImpl.class);
        User user = new User();
        user.setAccount("root");
        user.setPassword("root11");
        user.setIsAdmin(true);
        userController.register(user);

        IBookController bookController = ControllerFactory.getBean(BookControllerImpl.class);
        for (int i = 0; i < 5; i++) {
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("author"+i);
            bookItem.setInventory(i+1);
            bookController.addBook(bookItem);

        }



        new MainView().show();




    }
}