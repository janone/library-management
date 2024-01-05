package org.example.service;

import org.example.entity.BookItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BookItemServiceTest {

    private BookItemService bookItemService = new BookItemService();

    @BeforeEach
    void setUp() {
        IntStream.range(0,20).forEach(i->{
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("bookAuthor"+i);
            bookItem.setInventory(i+1);
            bookItemService.insert(bookItem);
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
        bookItemService.insert(bookItem);

        BookItem param = new BookItem();
        List<BookItem> list = bookItemService.list(param);
        System.out.println(list.size());

        param.setAuthor("insert");
        list = bookItemService.list(param);

        System.out.println(list.size());
        System.out.println(list);
    }

    @Test
    void delete() {
        BookItem bookItem = new BookItem();
        bookItem.setName("1");
        List<BookItem> list = bookItemService.list(bookItem);

        System.out.println(list.size());
        BookItem bookItem1 = list.get(0);
        bookItemService.delete(bookItem1.getUnionKey());

        List<BookItem> list1 = bookItemService.list();
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
        List<BookItem> list = bookItemService.list();
        BookItem bookItem3 = list.get(3);
        System.out.println(bookItem3);
        String newName = "updated book name";
        bookItem3.setName(newName);

        bookItemService.update(bookItem3);

        BookItem byId = bookItemService.getById(bookItem3.getUnionKey());
        System.out.println(byId);
        if(byId.getName().equals(newName)){
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    @Test
    void getById() {
    }

    @Test
    void list() {
    }

    @Test
    void testList() {
    }
}