package org.example.view;

import org.example.rpc.ClientFactory;
import org.example.common.Constants;
import org.example.common.Result;
import org.example.controller.IUserController;
import org.example.entity.User;
import org.example.rpc.Session;


public class UserLoginRegisterView extends View{


    private IUserController userController = ClientFactory.getController(IUserController.class);
    public Object show(User loginUser){
        System.out.println("====================================");
        System.out.println("Welcome to Library Management System");
        System.out.println("====================================");

        while(true){
            System.out.println("Please fill your account to Login or Register: ");

            String account = scanner.next();

            Result<User> result = userController.getUserByAccount(account);
            if(result.getCode().equals(Constants.FAIL_CODE)){
                System.out.println(result.getMsg());
                continue;
            }
            User data = result.getData();
            if(data == null){ // not exist, go register
                System.out.println("user " + account + " is not exist, " +
                        "please enter your password to register one, at least six characters. " +
                        "or you can enter N to try login again");
                String password = scanner.next();
                if(password.equals("N")){
                    System.out.println("you type N ");
                    continue;
                }


                User user = new User();
                user.setAccount(account);
                user.setPassword(password);
                Result<Boolean> registerResult = userController.register(user);
                if(registerResult.getCode().equals(Constants.SUCCESS_CODE)){
                    System.out.println("congratulations! you have successfully registered an account for "+account
                    +", you can upgrade your account to admin role by contacting Administrator");
                } else {
                    System.out.println(registerResult.getMsg());
                }

            } else { // exist, go password
                System.out.println("please enter you password to login");
                String password = scanner.next();
                Result<String> loginResult = userController.login(account, password);
                if(loginResult.isSuccess()){
                    System.out.println("you login success !!");
                    Session.getInstance().setSessionId(loginResult.getData()); // set token
                    return data;
                } else {
                    System.out.println(loginResult.getMsg());
                }
            }
        }

    }
}
