package org.example.controller;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.impl.BookControllerImpl;
import org.example.controller.impl.BorrowRecordControllerImpl;
import org.example.controller.impl.LibraryControllerImpl;
import org.example.controller.impl.UserControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.BorrowRecord;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ILibraryControllerTest {

    private ILibraryController libraryController;
    private IBookController bookController;
    private IUserController userController;
    private IBorrowRecordController borrowRecordController;

    @BeforeEach
    void setUp() {


        libraryController = ControllerFactory.getBean(LibraryControllerImpl.class);
        bookController = ControllerFactory.getBean(BookControllerImpl.class);
        userController = ControllerFactory.getBean(UserControllerImpl.class);
        borrowRecordController = ControllerFactory.getBean(BorrowRecordControllerImpl.class);

        IntStream.range(0,20).forEach(i->{
            BookItem bookItem = new BookItem();
            bookItem.setName("bookName"+i);
            bookItem.setAuthor("bookAuthor"+i);
            bookItem.setInventory(i+1);
            bookController.addBook(bookItem);

        });

        User user1 = new User();
        user1.setIsAdmin(true);
        user1.setPassword("123456");
        user1.setAccount("Jack");
        userController.register(user1);

        User user2 = new User();
        user1.setIsAdmin(false);
        user1.setPassword("123456");
        user1.setAccount("Lucy");
        userController.register(user2);

    }

    @Test
    void borrowBook() {
        Result<List<BorrowRecord>> record1 = borrowRecordController.getByUserAccount("Jack");
        libraryController.borrowBook("Jack", "bookName1", "bookAuthor1", 1);
        Result<List<BorrowRecord>> record2 = borrowRecordController.getByUserAccount("Jack");
        assertTrue(record1.getData().size() == 0 && record2.getData().size() == 1);
    }

    @Test
    void returnBook() {

        borrowBook();

        libraryController.returnBook("Jack", "bookName1", "bookAuthor1", 1);
        Result<List<BorrowRecord>> record2 = borrowRecordController.getByUserAccount("Jack");
        Result<BookItem> books = bookController.getBooks("bookAuthor1", "bookName1");
        assertEquals(2, (int) books.getData().getInventory());
        assertEquals(0,record2.getData().size());


    }
}