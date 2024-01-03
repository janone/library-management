package org.example.view;

import org.example.common.ReturnException;
import org.example.entity.User;

import java.util.Scanner;

public abstract class View {

    final Scanner scanner = new Scanner(System.in);
    static final String RETURN_COMMAND = "RT";

    protected void checkReturn(String command){
        if(command.equalsIgnoreCase(RETURN_COMMAND)){
            System.out.println(" -> return to home page");
            throw new ReturnException();
        }
    }

    /**
     * it will check if the command is return command, if it is, it will throw a ReturnException
     * @return
     */
    protected String scanNextWithCheckReturn(){
        String command = scanner.next();
        checkReturn(command);
        return command;
    }

    public User showRepetitively(User user){
        showTop(user);
        while(true){
            try {
                show(user);
            } finally {
                showBeforeReturn(user);
            }
        }
    }

    protected void showBeforeReturn(User user) {}

    protected void showTop(User user) {}

    public abstract Object show(User user);



}
