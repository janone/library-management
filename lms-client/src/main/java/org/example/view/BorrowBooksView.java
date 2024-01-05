package org.example.view;

import org.example.common.BeanFactory;
import org.example.common.Result;
import org.example.controller.ILibraryController;
import org.example.controller.impl.LibraryControllerImpl;
import org.example.entity.User;

public class BorrowBooksView extends View{

    private ILibraryController libraryController = BeanFactory.getBean(LibraryControllerImpl.class);

    public Object show(User user) {

        System.out.println("-------  Borrow Books  -------");

        System.out.println("please enter book name");
        String bookName = scanNextWithCheckReturn();

        System.out.println("please enter book author");
        String bookAuthor = scanNextWithCheckReturn();

        System.out.println("please enter the amount of book you want to borrow");
        String amount = scanNextWithCheckReturn();
        if(!amount.matches("\\d+")){
            System.out.println("amount should be a number format. please try again");
            return null;
        }

        Result<Boolean> booleanResult = libraryController.borrowBook(user.getAccount(), bookName, bookAuthor, Integer.valueOf(amount));
        if(booleanResult.isSuccess()){
            System.out.println("borrow success");
        }else{
            System.out.println(booleanResult.getMsg());
        }

        return null;

    }
}
