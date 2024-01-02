package org.example.manager;

import org.example.annotation.AutoWiredField;
import org.example.common.BusinessException;
import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.IBorrowRecordController;
import org.example.entity.BookItem;
import org.example.entity.BorrowRecord;
import org.example.service.BookItemService;
import org.example.service.BorrowRecordService;

public class LibraryManager {

    @AutoWiredField
    private BookItemService bookItemService;
    @AutoWiredField
    private BorrowRecordService borrowRecordService;


    public Boolean borrowBook(String userAccount, String bookName, String author, Integer amount) {

        BookItem book = bookItemService.getById(BookItem.generateUnionKey(author, bookName));
        if(book == null){ throw new BusinessException("book does not exist!"); }

        Integer inventory = book.getInventory();
        if(amount > inventory){ throw new IllegalArgumentException("The book inventory is not enough! current inventory is " + inventory + ", but you want " + amount);}

        BorrowRecord borrowRecord = borrowRecordService.getById(BorrowRecord.generateUnionKey(userAccount,book.getUnionKey()));
        if (borrowRecord == null) {
            borrowRecord = new BorrowRecord(userAccount, book.getUnionKey(), amount);
            borrowRecordService.insert(borrowRecord);
        } else {
            borrowRecord.setAmount(borrowRecord.getAmount() + amount);
            borrowRecordService.update(borrowRecord);
        }

        book.setInventory(book.getInventory() - amount);
        bookItemService.update(book);

        return true;
    }


    public Boolean returnBook(String userAccount, String bookName, String author, Integer amount) {

        BookItem book = bookItemService.getById(BookItem.generateUnionKey(author, bookName));
        if(book == null){ throw new BusinessException("book does not exist!"); }


        String recordUnionKey = BorrowRecord.generateUnionKey(userAccount, book.getUnionKey());
        BorrowRecord borrowRecord = borrowRecordService.getById(recordUnionKey);
        if (borrowRecord == null) {
            throw new IllegalStateException("no borrow record");
        }

        if(borrowRecord.getAmount() - amount == 0){
            borrowRecordService.delete(recordUnionKey);

        } else if(borrowRecord.getAmount() - amount < 0){
            throw new IllegalStateException("the amount you return is greater than you borrowed");

        } else {
            borrowRecord.setAmount(borrowRecord.getAmount() - amount);
            borrowRecordService.update(borrowRecord);
        }

        book.setInventory(book.getInventory() + amount);
        bookItemService.update(book);

        return true;
    }

}