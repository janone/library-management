package org.example.controller;

import org.example.common.Result;
import org.example.entity.BookItem;

import java.util.Collection;

public interface IBookController extends IController {

    /**
     * add book
     * @param bookItem
     * @return BookItem
     */
    Result<BookItem> addBook(BookItem bookItem);

    /**
     * delBooks by author and name
     * @param author
     * @param name
     * @return
     */
    Result<Boolean> delBooks(String author, String name);

    /**
     * get a unique book by author and name
     * @param author
     * @param name
     * @return
     */
    Result<BookItem> getBooks(String author, String name);

    /**
     * list by keyword
     * @param keyword
     * @return
     */
    Result<Collection<BookItem>> listBooksByKeyword(String keyword);

}
