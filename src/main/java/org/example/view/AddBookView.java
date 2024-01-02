package org.example.view;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.impl.BookControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.User;

public class AddBookView implements View{


    private IBookController bookController = ControllerFactory.getBean(BookControllerImpl.class);
    public void show(User user){


        if(!user.getIsAdmin()){
            System.out.println("only admin can add books. please ask for admin permission first");
        }

        while (true) {
            System.out.println("please enter book name");
            String bookName = scanner.next();
            checkReturn(bookName);

            System.out.println("please enter book author");

            String bookAuthor = scanner.next();
            checkReturn(bookAuthor);


            System.out.println("please enter the inventory");
            String inventory = scanner.next();
            checkReturn(inventory);

            BookItem bookItem = new BookItem(bookName, bookAuthor, Integer.valueOf(inventory));
            Result<BookItem> bookItemResult = bookController.addBook(bookItem);

            if(bookItemResult.isSuccess()){
                System.out.println("add book success");
            }else{
                System.out.println(bookItemResult.getMsg()+", please try again");
            }
        }

    }
}
