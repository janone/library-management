package org.example.controller;

import org.example.common.Result;
import org.example.entity.BookItem;

import java.util.Collection;

/**
 * for bookItem crud
 */
public interface IBookController extends IController {

    /**
     * add book. when book exist. add the inventory only
     * @param bookItem
     * @return BookItem
     */
    Result<BookItem> addBook(BookItem bookItem);

    /**
     * delBooks by book author and book name
     * if the book is borrowed then Result in fail and tip the user
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
     * it will search the book by name 'or' author
     * @param keyword
     * @return
     */
    Result<Collection<BookItem>> listBooksByKeyword(String keyword);

}
