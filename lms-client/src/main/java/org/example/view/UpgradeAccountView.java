package org.example.view;

import org.example.rpc.ClientFactory;
import org.example.common.Result;
import org.example.common.ReturnException;
import org.example.controller.IUserController;
import org.example.entity.User;

public class UpgradeAccountView extends View{

    private IUserController userController = ClientFactory.getController(IUserController.class);

    @Override
    protected void showTop(User user) {
        if(!user.getIsAdmin()){
            System.out.println("only admin can add books. please ask for admin permission first");
            throw new ReturnException();
        }
    }
    public Object show(User user) {

        System.out.println("-------  Upgrade Account  -------");

        System.out.println("please enter an account");
        String account = scanNextWithCheckReturn();

        Result<User> userByAccount = userController.getUserByAccount(account);
        if(userByAccount.isSuccess()){
            if(userByAccount.getData().getIsAdmin()){
                System.out.println("user is already admin.");
            }else{
                Result<Boolean> upgrade = userController.upgrade(account);
                if(upgrade.isSuccess()){
                    System.out.println("upgrade success");
                } else {
                    System.out.println(upgrade.getMsg());
                }
            }
        }
        return null;

    }
}
