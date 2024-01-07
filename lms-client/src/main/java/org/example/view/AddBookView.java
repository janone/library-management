package org.example.view;

import org.example.rpc.ClientFactory;
import org.example.common.Result;
import org.example.common.ReturnException;
import org.example.controller.IBookController;
import org.example.entity.BookItem;
import org.example.entity.User;

public class AddBookView extends View{


    private IBookController bookController = ClientFactory.getController(IBookController.class);

    @Override
    protected void showTop(User user) {
        if(!user.getIsAdmin()){
            System.out.println("only admin can add books. please ask for admin permission first");
            throw new ReturnException();
        }
    }

    @Override
    public Object show(User user){

        System.out.println("-------  Add Books  -------");

        System.out.println("please enter book name");
        String bookName = scanNextWithCheckReturn();

        System.out.println("please enter book author");

        String bookAuthor = scanNextWithCheckReturn();



        System.out.println("please enter the inventory");
        String inventory = scanNextWithCheckReturn();
        if(!inventory.matches("\\d+")){
            System.out.println("inventory should be a number format. please try again");
            return null;
        }

        BookItem bookItem = new BookItem(bookName, bookAuthor, Integer.valueOf(inventory));
        Result<BookItem> bookItemResult = bookController.addBook(bookItem);

        if(bookItemResult.isSuccess()){
            System.out.println("add book success");
        }else{
            System.out.println(bookItemResult.getMsg()+", please try again");
        }

        return null;

    }
}
