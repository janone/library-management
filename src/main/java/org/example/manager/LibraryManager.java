package org.example.manager;

import org.example.annotation.AutoWiredField;
import org.example.common.BusinessException;
import org.example.entity.BookItem;
import org.example.entity.BorrowRecord;
import org.example.service.BookItemService;
import org.example.service.BorrowRecordService;

/**
 * process book borrow and return businesses
 */
public class LibraryManager {

    @AutoWiredField
    private BookItemService bookItemService;
    @AutoWiredField
    private BorrowRecordService borrowRecordService;

    /**
     * the borrow book business
     * @param userAccount
     * @param bookName
     * @param author
     * @param amount
     * @return
     */
    public Boolean borrowBook(String userAccount, String bookName, String author, Integer amount) {

        // check book exist
        BookItem book = bookItemService.getById(BookItem.generateUnionKey(author, bookName));
        if(book == null){ throw new BusinessException("book does not exist!"); }

        // check book inventory enough
        Integer inventory = book.getInventory();
        if(amount > inventory){ throw new BusinessException("The book inventory is not enough! current inventory is " + inventory + ", but you want " + amount);}

        BorrowRecord borrowRecord = borrowRecordService.getById(BorrowRecord.generateUnionKey(userAccount,book.getUnionKey()));
        if (borrowRecord == null) { // if record not exist then create one
            borrowRecord = new BorrowRecord(userAccount, book.getUnionKey(), amount);
            borrowRecordService.insert(borrowRecord);

        } else { // if record exist then add amount
            borrowRecord.setAmount(borrowRecord.getAmount() + amount);
            borrowRecordService.update(borrowRecord);
        }

        // set book inventory
        book.setInventory(book.getInventory() - amount);
        bookItemService.update(book);

        return true;
    }


    /**
     * the return book business
     * @param userAccount
     * @param bookName
     * @param author
     * @param amount
     * @return
     */
    public Boolean returnBook(String userAccount, String bookName, String author, Integer amount) {

        // check if book exist
        BookItem book = bookItemService.getById(BookItem.generateUnionKey(author, bookName));
        if(book == null){ throw new BusinessException("book does not exist!"); }


        // check if record exist
        String recordUnionKey = BorrowRecord.generateUnionKey(userAccount, book.getUnionKey());
        BorrowRecord borrowRecord = borrowRecordService.getById(recordUnionKey);
        if (borrowRecord == null) {
            throw new BusinessException("no borrow record");
        }

        // if amount is equal to borrow record amount then delete record
        if(borrowRecord.getAmount() - amount == 0){
            borrowRecordService.delete(recordUnionKey);

        } else if(borrowRecord.getAmount() - amount < 0){ // if amount is greater than borrow record amount then tip user illegal operation
            throw new BusinessException("the amount you return is greater than you borrowed");

        } else { // if amount is less than borrow record amount then update record
            borrowRecord.setAmount(borrowRecord.getAmount() - amount);
            borrowRecordService.update(borrowRecord);
        }

        // update bookItem inventory
        book.setInventory(book.getInventory() + amount);
        bookItemService.update(book);

        return true;
    }

}