package org.example.controller;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.impl.BookControllerImpl;
import org.example.entity.BookItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IBookControllerTest {

    private IBookController bookController;
    @BeforeEach
    void setUp() {

        ControllerFactory.getInstance().registerToBeanFactory(
                new BookControllerImpl()
        );

        bookController = ControllerFactory.getBean(IBookController.class);

        IntStream.range(0,20).forEach(i->{
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("bookAuthor"+i);
            bookItem.setInventory(i+1);
            bookController.addBook(bookItem);
        });
    }

    @Test
    void addBook() {

        BookItem bookItem = new BookItem("test author", "test name", 100);
        Result<BookItem> bookItemResult = bookController.addBook(bookItem);

        System.out.println(bookItemResult.getData());

        Result<Collection<BookItem>> test = bookController.listBooksByKeyword("test");
        Collection<BookItem> dataList = test.getData();
        for (BookItem item : dataList) {
            System.out.println(item);
            assertEquals("[test name]-[test author]", item.getUnionKey());
        }


    }

    @Test
    void delBooks() {

        bookController.delBooks("bookAuthor1", "bookName1");

        assertTrue(bookController.listBooksByKeyword("bookName1").getData().isEmpty());;


    }

    @Test
    void getBooks() {
        assertNotNull(bookController.getBooks("bookAuthor1", "bookName1"));
    }

    @Test
    void listBooks() {
        assertNotNull(bookController.listBooksByKeyword("bookName1"));
    }
}