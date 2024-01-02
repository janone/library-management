package org.example.view;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.ILibraryController;
import org.example.controller.impl.LibraryControllerImpl;
import org.example.entity.User;

public class BorrowBooksView implements View{

    private ILibraryController libraryController = ControllerFactory.getBean(LibraryControllerImpl.class);

    public void show(User user) {


        while(true){
            System.out.println("please enter book name");
            String bookName = scanner.next();
            checkReturn(bookName);

            System.out.println("please enter book author");
            String bookAuthor = scanner.next();
            checkReturn(bookAuthor);

            System.out.println("please enter the amount of book you want to borrow");
            String amount = scanner.next();
            checkReturn(amount);

            Result<Boolean> booleanResult = libraryController.borrowBook(user.getAccount(), bookName, bookAuthor, Integer.valueOf(amount));
            if(booleanResult.isSuccess()){
                System.out.println("borrow success");
            }else{
                System.out.println(booleanResult.getMsg());
            }

        }


    }
}
