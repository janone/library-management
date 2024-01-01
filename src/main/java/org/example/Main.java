package org.example;

import org.example.common.ControllerFactory;
import org.example.controller.impl.UserControllerImpl;
import org.example.view.MainView;

import java.util.Scanner;

/**
 * @Author: Zeng Jian
 * @DateTime: 2023-12-19 14:58
 * @Description: Library Management	System Entrance
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner(){
        return scanner;
    }
    public static void main(String[] args) {

        ControllerFactory.getInstance().registerToBeanFactory(
                new UserControllerImpl()
        );


        new MainView().show();




    }
}