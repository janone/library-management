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
            if(bookName.equals(RETURN_COMMAND))return;

            System.out.println("please enter book author");
            String bookAuthor = scanner.next();
            if(bookAuthor.equals(RETURN_COMMAND))return;

            Result<Boolean> booleanResult = bookController.delBooks(bookAuthor, bookName);
            if(booleanResult.isSuccess()){
                System.out.println("delete success");
            }else{
                System.out.println(booleanResult.getMsg());
            }
        }





    }

}
