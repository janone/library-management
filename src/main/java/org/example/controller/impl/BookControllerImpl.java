package org.example.controller.impl;

import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.IUserController;
import org.example.entity.BookItem;
import org.example.entity.User;
import org.example.service.BookItemService;
import org.example.service.UserService;

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


        return Result.successWithData(bookItemService.insert(bookItem));
    }


}
