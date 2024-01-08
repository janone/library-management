package org.example.entity;


import org.example.annotation.IDField;

import java.io.Serializable;
import java.util.Map;

/**
 * a bookItem is a unit for book management
 */
public class BookItem implements Serializable {



    /**
     * book id: the format is "["+author + "]-[" + book_name + "]"
     */
    @IDField
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
    private Integer inventory;


    /**
     * book borrow records
     * the key is user account
     */
    private Map<String, BorrowRecord> borrowRecords;

    public BookItem(String name, String author, Integer inventory) {
        this.name = name;
        this.author = author;
        this.inventory = inventory;
        this.unionKey = generateUnionKey(this.author, this.name);
    }

    public BookItem() {
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


    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
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

    @Override
    public String toString() {
        return "BookItem{" +
                "unionKey='" + unionKey + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", inventory=" + inventory +
                '}';
    }

    @Override
    public int hashCode() {
        return BookItem.generateUnionKey(this.author,this.name).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        BookItem newItem = (BookItem)obj;
        return BookItem.generateUnionKey(this.author,this.name)
                .equals(
                BookItem.generateUnionKey(newItem.getAuthor(),newItem.getName())
        );
    }




}
