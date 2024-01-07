package org.example.view;

import org.example.rpc.ClientFactory;
import org.example.common.Result;
import org.example.controller.ILibraryController;
import org.example.entity.User;

public class ReturnBooksView extends View{

    private ILibraryController libraryController = ClientFactory.getController(ILibraryController.class);

    public Object show(User user) {
        System.out.println("-------  Return Books  -------");

        System.out.println("please enter book name");
        String bookName = scanNextWithCheckReturn();

        System.out.println("please enter book author");
        String bookAuthor = scanNextWithCheckReturn();

        System.out.println("please enter the amount of book you want to return");
        String amount = scanNextWithCheckReturn();
        if(!amount.matches("\\d+")){
            System.out.println("amount should be a number format. please try again");
            return null;
        }

        Result<Boolean> booleanResult = libraryController.returnBook(user.getAccount(), bookName, bookAuthor, Integer.valueOf(amount));
        if(booleanResult.isSuccess()){
            System.out.println("return book success");
        }else{
            System.out.println(booleanResult.getMsg());
        }

        return null;

    }

}
