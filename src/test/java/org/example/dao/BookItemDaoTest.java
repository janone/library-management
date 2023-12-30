package org.example.dao;

import org.example.entity.BookItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BookItemDaoTest {

    BookItemDao bookItemDao = new BookItemDao();

    @BeforeEach
    void setUp() {
        IntStream.range(0,20).forEach(i->{
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("bookAuthor"+i);
            bookItem.setInventory(i+1);
            bookItemDao.insert(bookItem);
        });
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {

        BookItem bookItem = new BookItem();
        bookItem.setName("insert book name A");
        bookItem.setAuthor("insert book author A");
        bookItem.setInventory(10);
        bookItemDao.insert(bookItem);

        BookItem param = new BookItem();
        List<BookItem> list = bookItemDao.list(param);
        System.out.println(list.size());

        param.setAuthor("insert");
        list = bookItemDao.list(param);

        System.out.println(list.size());
        System.out.println(list);




    }

    @Test
    void delete() {
        BookItem bookItem = new BookItem();
        bookItem.setName("1");
        List<BookItem> list = bookItemDao.list(bookItem);

        System.out.println(list.size());
        BookItem bookItem1 = list.get(0);
        bookItemDao.delete(bookItem1.getUnionKey());

        List<BookItem> list1 = bookItemDao.list();
        System.out.println(list1.size());

        boolean exist = list1.stream().anyMatch(b -> b.getUnionKey().equals(bookItem1.getUnionKey()));
        if(exist){
            throw new RuntimeException("book still exist");
        }else{
            System.out.println("remove success");
        }

    }

    @Test
    void update() {

        List<BookItem> list = bookItemDao.list();
        BookItem bookItem3 = list.get(3);
        System.out.println(bookItem3);
        String newName = "updated book name";
        bookItem3.setName(newName);

        bookItemDao.update(bookItem3);

        BookItem byId = bookItemDao.getById(bookItem3.getUnionKey());
        System.out.println(byId);

    }

    @Test
    void getById() {
    }

    @Test
    void list() {
        BookItem bookItem = new BookItem();
        bookItem.setAuthor("au");
        List<BookItem> list = bookItemDao.list(bookItem);

        System.out.println(list);

    }
}