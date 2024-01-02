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
            System.out.println("please enter book name, or enter RT to return main page");
            String bookName = scanner.next();
            if(bookName.equals("R"))return;

            System.out.println("please enter book author, or enter RT to return main page");

            String bookAuthor = scanner.next();
            if(bookAuthor.equals("R"))return;


            System.out.println("please enter the inventory, or enter R to return main page");
            String inventory = scanner.next();
            if(inventory.equals("R"))return;

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
