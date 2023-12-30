package org.example.dao;

import org.example.entity.BookItem;

import java.util.*;

public class BookItemDao {

    Map<String, BookItem> bookItemStorage = new HashMap<>();


    public boolean insert(BookItem bookItem){
        bookItemStorage.put(bookItem.getUnionKey(),bookItem);
        return true;
    }

    public boolean delete(String unionKey){
        bookItemStorage.remove(unionKey);
        return true;
    }

    public boolean update(BookItem bookItem){
        bookItemStorage.put(bookItem.getUnionKey(),bookItem);
        return true;
    }

    public BookItem getById(String id){
        return bookItemStorage.get(id);
    }

    public List<BookItem> list(BookItem bookItem){
        Collection<BookItem> values = bookItemStorage.values();
        List<BookItem> result = new ArrayList<>();

        if(bookItem.getAuthor() != null){
            for (BookItem v : values) {
                if(v.getAuthor().toLowerCase().contains(bookItem.getAuthor())){
                    result.add(v);
                }
            }
        }

        if(bookItem.getName() != null){
            for (BookItem v : values) {
                if(v.getName().toLowerCase().contains(bookItem.getName())){
                    result.add(v);
                }
            }
        }

        return result;
    }

}
