package org.example.view;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IUserController;
import org.example.entity.User;

public class UpgradeAccountView implements View{

    private IUserController userController = ControllerFactory.getBean(IUserController.class);
    public void show(User user) {


        while(true){
            System.out.println("please enter an account");
            String account = scanner.next();
            checkReturn(account);

            Result<User> userByAccount = userController.getUserByAccount(account);
            if(userByAccount.isSuccess()){
                if(userByAccount.getData().getIsAdmin()){
                    System.out.println("user is already admin.");
                }else{
                    userController.upgrade(account);
                }
            }
        }

    }
}