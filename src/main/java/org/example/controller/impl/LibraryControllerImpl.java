package org.example.controller.impl;

import org.example.annotation.AutoWiredField;
import org.example.common.Result;
import org.example.controller.ILibraryController;
import org.example.entity.BookItem;
import org.example.manager.LibraryManager;

public class LibraryControllerImpl implements ILibraryController {
    @AutoWiredField
    private LibraryManager libraryManager;


    @Override
    public Result<Boolean> borrowBook(String userAccount, String bookName, String author, Integer amount) {
        synchronized (BookItem.generateUnionKey(author, bookName).intern()){
            libraryManager.borrowBook(userAccount, bookName, author, amount);
        }
        return Result.success();
    }

    @Override
    public Result<Boolean> returnBook(String userAccount, String bookName, String author, Integer amount) {
        synchronized (BookItem.generateUnionKey(author, bookName).intern()){
            libraryManager.returnBook(userAccount, bookName, author, amount);
        }
        return Result.success();
    }

}
