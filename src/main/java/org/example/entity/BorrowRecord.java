package org.example.entity;

import org.example.annotation.IDField;

/**
 * this is the borrow record
 */
public class BorrowRecord {

    @IDField
    private String unionKey;

    /**
     * user account
     */
    private String userAccount;

    /**
     * bookUnionKey
     */
    private String bookUnionKey;

    /**
     * how many book does this user borrow
     */
    private int amount;

    public BorrowRecord(String userAccount, String bookUnionKey, Integer amount) {
        this.userAccount = userAccount;
        this.bookUnionKey = bookUnionKey;
        this.amount = amount;
        this.unionKey = BorrowRecord.generateUnionKey(userAccount,bookUnionKey);
    }

    public BorrowRecord(){}

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getBookUnionKey() {
        return bookUnionKey;
    }

    public void setBookUnionKey(String bookUnionKey) {
        this.bookUnionKey = bookUnionKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static String generateUnionKey(String userAccount, String bookUnionKey) {
        return "[" + userAccount + "]-" + bookUnionKey;
    }

}
