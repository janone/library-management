package org.example.view;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.entity.User;

public class DeleteBookView implements View{

    private IBookController bookController = ControllerFactory.getBean(IBookController.class);

    public void show(User user) {


        while(true){
            System.out.println("please enter book name");
            String bookName = scanner.next();
            checkReturn(bookName);

            System.out.println("please enter book author");
            String bookAuthor = scanner.next();
            checkReturn(bookAuthor);

            Result<Boolean> booleanResult = bookController.delBooks(bookAuthor, bookName);
            if(booleanResult.isSuccess()){
                System.out.println("delete success");
            }else{
                System.out.println(booleanResult.getMsg());
            }
        }





    }

}
