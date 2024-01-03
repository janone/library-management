package org.example.controller;

import org.example.common.Result;

/**
 * an integrated interface to process multiple entity business.
 */
public interface ILibraryController extends IController{

    /**
     * borrow a book.
     * if the book is not enough, cause the operation to fail.
     *
     * @param userAccount the user account.
     * @param bookName the book name.
     * @param author the book author.
     * @param amount the amount of the book.
     * @return the result of the operation.
     */
    Result<Boolean> borrowBook(String userAccount, String bookName, String author, Integer amount);

    Result<Boolean> returnBook(String userAccount, String bookName, String author, Integer amount);

}
