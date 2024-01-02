package org.example.view;

import org.example.Main;
import org.example.entity.User;

import java.util.Scanner;

public class MainView {

    private static User loginUser = null;

    public void show(){

        Scanner scanner = Main.getScanner();

        loginUser = new UserLoginRegisterView().show();// login success

        if(loginUser == null) {
            System.out.println("unexpected login failed. system exit");
            return;
        }

        while(true){
            System.out.println("please choose an operation");
            if(loginUser.isAdmin()){
                System.out.println("A: add books");
                System.out.println("D: delete books");
                System.out.println("U: update books");
                System.out.println("G: upgrade an account to admin");
            }
            System.out.println("L: list all books");
            System.out.println("S: search books");
            System.out.println("B: borrow books");
            System.out.println("R: return books");
            System.out.println("O: login out");
            System.out.println("note: you can type RT at any time to return to this page.");

            String operation = scanner.next();

            if(loginUser.isAdmin()){
                if(operation.equals("A")){
                    new AddBookView().show(loginUser);
                }
                if(operation.equals("D")){
                    new AddBookView().show(loginUser);
                }
            }


        }



    }

}
