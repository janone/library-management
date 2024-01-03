package org.example.service;

import org.example.dao.BookItemDao;
import org.example.entity.BookItem;

import java.util.*;

/**
 * book item crud service
 */
public class BookItemService extends BaseService<BookItemDao, BookItem>{


    public Boolean addBook(BookItem bookItem){

        String unionKey = BookItem.generateUnionKey(bookItem.getAuthor(), bookItem.getName());

        // check if the book exist
        BookItem bookItemDB = this.getById(unionKey);

        if(bookItemDB != null) { // when exist one, then increase the inventory
            bookItem.setInventory( bookItemDB.getInventory() + bookItem.getInventory() );
            return this.update(bookItem);
        } else { // when not exist, create new one
            this.insert(bookItem);
        }
        return true;
    }

    /**
     * list by author or book name
     * @param keyword
     * @return
     */
    public Collection<BookItem> listByKeyWord(String keyword) {

        if(keyword == null || keyword.equals("")){
            return this.list();
        }

        BookItem param1 = new BookItem();
        param1.setName(keyword);
        BookItem param2 = new BookItem();
        param2.setAuthor(keyword);

        Set<BookItem> set1 = new HashSet<>(this.list(param1));
        Set<BookItem> set2 = new HashSet<>(this.list(param2));
        set1.addAll(set2);


        return set1;

    }
}
