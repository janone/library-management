package org.example.view;

import org.example.common.ReturnException;
import org.example.entity.User;

public class MainView implements View{

    private static User loginUser = null;

    public void show(){

        loginUser = new UserLoginRegisterView().show();// login success

        if(loginUser == null) {
            System.out.println("unexpected login failed. system exit");
            return;
        }

        while(true){
            System.out.println("please choose an operation");
            if(loginUser.getIsAdmin()){
                System.out.println("A: add books");
                System.out.println("D: delete books");
                System.out.println("G: upgrade an account to admin");
            }
            System.out.println("L: list all books");
            System.out.println("S: search books");
            System.out.println("B: borrow books");
            System.out.println("R: return books");
            System.out.println("O: login out");
            System.out.println("note: you can type " + scanner + " at any time to return to this page.");

            String operation = scanner.next();

            try{
                if(loginUser.getIsAdmin()){
                    if(operation.equals("A")){
                        new AddBookView().show(loginUser);
                    }
                    if(operation.equals("D")){
                        new DeleteBookView().show(loginUser);
                    }
                    if(operation.equals("G")){
                        new UpgradeAccountView().show(loginUser);
                    }
                }
            } catch (ReturnException e){
                // do nothing. go to next loop
            }



        }



    }

}
