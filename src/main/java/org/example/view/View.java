package org.example.view;

import org.example.common.ReturnException;

import java.util.Scanner;

public interface View {

    final Scanner scanner = new Scanner(System.in);
    static final String RETURN_COMMAND = "RT";

    default void checkReturn(String command){
        if(command.equals(RETURN_COMMAND)){
            System.out.println("返回上一级");
            throw new ReturnException();
        }
    }



}
