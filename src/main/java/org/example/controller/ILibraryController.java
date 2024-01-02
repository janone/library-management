package org.example.controller;

import org.example.common.Result;

public interface ILibraryController extends IController{

    Result<Boolean> borrowBook(String userAccount, String bookName, String author, Integer amount);

    Result<Boolean> returnBook(String userAccount, String bookName, String author, Integer amount);

}
