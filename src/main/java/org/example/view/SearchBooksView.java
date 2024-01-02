package org.example.view;
import org.example.common.ControllerFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.impl.BookControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.User;

import java.util.Collection;

public class SearchBooksView implements View{

    private IBookController bookController = ControllerFactory.getBean(BookControllerImpl.class);

    public void show(User user) {

        while (true){

            System.out.println("please enter search keyword");
            String keyword = scanner.next();
            checkReturn(keyword);

            Result<Collection<BookItem>> collectionResult = bookController.listBooksByKeyword(keyword);
            if (!collectionResult.isSuccess()) {
                System.out.println(collectionResult.getMsg());
            } else {
                Collection<BookItem> data = collectionResult.getData();
                for (BookItem bookItem : data) {
                    System.out.println(bookItem.getName() + "\t" + bookItem.getAuthor() + "\t" + bookItem.getInventory());
                }
            }

        }
    }
}
