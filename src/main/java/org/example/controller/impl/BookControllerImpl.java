package org.example.controller.impl;

import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.IUserController;
import org.example.entity.BookItem;
import org.example.entity.User;
import org.example.service.BookItemService;
import org.example.service.UserService;

import java.util.List;

public class BookControllerImpl implements IBookController {

    private BookItemService bookItemService = new BookItemService();

    public Result<BookItem> addBook(BookItem bookItem){
        if(bookItem == null){
            throw new RuntimeException("bookItem should not be null");
        }
        if(bookItem.getName() == null || bookItem.getName().trim().equals("")){
            throw new RuntimeException("book name should not be empty");
        }
        if(bookItem.getAuthor() == null || bookItem.getAuthor().trim().equals("")){
            throw new RuntimeException("book author should not be empty");
        }
        if(bookItem.getInventory() == null || bookItem.getInventory() == 0){
            throw new RuntimeException("inventory should not be null");
        }

        String unionKey = BookItem.generateUnionKey(bookItem.getAuthor(), bookItem.getName());

        // do it inside the synchronized key word to make it atomic.
        // do not synchronize inside the add-method only, because
        // to make sure the atomicity can be spread in the whole lifecycle.
        // in case of the service is wrapped by a transaction proxy.
        synchronized (unionKey.intern()){
            bookItemService.addBook(bookItem);
        }

        return Result.success();
    }

    public Result<Boolean> delBooks(BookItem bookItem) {
        if(bookItem.getName() == null || bookItem.getName().trim().equals("")){
            throw new RuntimeException("please enter the book name");
        }

        if(bookItem.getAuthor() == null || bookItem.getAuthor().trim().equals("")){
            throw new RuntimeException("please enter the book author");
        }

        // check if data exist
        String unionKey = BookItem.generateUnionKey(bookItem.getAuthor(), bookItem.getName());
        BookItem byId = bookItemService.getById(unionKey);
        if(byId == null){
            throw new RuntimeException("the book does not exist");
        }

        // delete if exist
        bookItemService.delete(unionKey);

        return Result.success();

    }


}
