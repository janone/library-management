package org.example.view;

import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.entity.BookItem;
import org.example.entity.User;

import java.util.Collection;


public class ListBooksView implements View{

    private IBookController bookController = ControllerFactory.getBean(IBookController.class);

    public void show(User user) {

        System.out.println("book-name\tbook-author\tinventory");
        Result<Collection<BookItem>> collectionResult = bookController.listBooksByKeyword("");
        if (!collectionResult.isSuccess()) {
            System.out.println(collectionResult.getMsg());
            return;
        }
        Collection<BookItem> data = collectionResult.getData();
        for (BookItem bookItem : data) {
            System.out.println(bookItem.getName() + "\t" + bookItem.getAuthor() + "\t" + bookItem.getInventory());
        }


    }

}
