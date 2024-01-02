package org.example.service;

import org.example.dao.BookItemDao;
import org.example.entity.BookItem;

import java.util.*;

public class BookItemService extends BaseService<BookItemDao, BookItem>{

    public Boolean addBook(BookItem bookItem){

        bookItem.setUnionKey(BookItem.generateUnionKey(bookItem.getAuthor(),bookItem.getName()));


        // check if the book exist
        BookItem param = new BookItem();
        param.setName(bookItem.getName());
        param.setAuthor(bookItem.getAuthor());
        List<BookItem> list = this.list(param);

        if(list.size() == 1) { // when exist one, then increase the inventory
            BookItem bookItemDB = list.get(0);
            bookItemDB.setInventory(bookItemDB.getInventory()+bookItem.getInventory());
            return this.update(bookItem);
        } else if(list.size() == 0){ // when not exist, create new one
            this.insert(bookItem);
        } else { // to warn the unexpected data
            throw new RuntimeException("duplicated book item data appear");
        }

        return true;
    }

    public Collection<BookItem> listByKeyWord(String keyword) {
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
