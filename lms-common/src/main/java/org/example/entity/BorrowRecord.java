package org.example.entity;

import org.example.annotation.IDField;

/**
 * borrow record. record the borrow info
 * who borrowed which book for how many
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
    private Integer amount;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public static String generateUnionKey(String userAccount, String bookUnionKey) {
        return "[" + userAccount + "]-" + bookUnionKey;
    }

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }
}
