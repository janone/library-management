package org.example.view;

import org.example.rpc.ClientFactory;
import org.example.common.Result;
import org.example.common.ReturnException;
import org.example.controller.IBookController;
import org.example.entity.User;

public class DeleteBookView extends View{

    private IBookController bookController = ClientFactory.getController(IBookController.class);
    @Override
    protected void showTop(User user) {
        if(!user.getIsAdmin()){
            System.out.println("only admin can add books. please ask for admin permission first");
            throw new ReturnException();
        }
    }
    public Object show(User user) {

        System.out.println("-------  Delete Books  -------");

        System.out.println("please enter book name");
        String bookName = scanNextWithCheckReturn();

        System.out.println("please enter book author");
        String bookAuthor = scanNextWithCheckReturn();

        Result<Boolean> booleanResult = bookController.delBooks(bookAuthor, bookName);
        if(booleanResult.isSuccess()){
            System.out.println("delete success");
        }else{
            System.out.println(booleanResult.getMsg());
        }

        return null;

    }

}
