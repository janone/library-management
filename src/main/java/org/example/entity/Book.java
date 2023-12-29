package org.example.entity;

import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

/**
 * Book entity
 */
public class Book {
    /**
     * book id: the format is "["+author + "]-[" + book_name + "]"
     */
    private String unionKey;

    /**
     * book name.
     */
    private String name;

    /**
     * book author
     */
    private String author;

    /**
     * book inventory
     */
    private int inventory;


    /**
     * book borrow records
     * the key is user account
     */
    private Map<String, BorrowRecord> borrowRecords;

    public Book(String name, String author, int inventory) {
        this.name = name;
        this.author = author;
        this.inventory = inventory;
        this.unionKey = generateUnionKey(this.author, this.name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getUnionKey() {
        if (unionKey == null) {
            unionKey = generateUnionKey(this.author, this.name);
        }
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public static String generateUnionKey(String author, String name) {
        return "[" + author + "]-[" + name + "]"; // the compiler will wrap the string with StringBuilder automatically
    }

    public synchronized boolean borrow(String userAccount, int amount) {

        if(amount > this.inventory){
            throw new IllegalArgumentException("The book inventory is not enough! current inventory is " + this.inventory + ", but you want " + amount);
        }

        BorrowRecord borrowRecord = borrowRecords.get(userAccount);
        if (borrowRecord == null) {
            borrowRecord = new BorrowRecord(userAccount, this.getUnionKey(), amount);
            borrowRecords.put(userAccount, borrowRecord);
        } else {
            borrowRecord.setAmount(borrowRecord.getAmount() + amount);
        }
        this.inventory -= amount;

        return true;
    }

    /**
     * this is the borrow record
     */
    public static class BorrowRecord {

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

        public BorrowRecord(String userAccount, String bookUnionKey, int amount) {
            this.userAccount = userAccount;
            this.bookUnionKey = bookUnionKey;
            this.amount = amount;
        }

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
    }
}
