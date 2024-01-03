package org.example.view;
import org.example.common.BeanFactory;
import org.example.common.Result;
import org.example.controller.IBookController;
import org.example.controller.impl.BookControllerImpl;
import org.example.entity.BookItem;
import org.example.entity.User;

import java.util.Collection;

public class SearchBooksView extends View{

    private IBookController bookController = BeanFactory.getBean(BookControllerImpl.class);

    @Override
    public Object show(User user) {

        System.out.println("-------  Search Books  -------");


        System.out.println("please enter search keyword");
        String keyword = scanNextWithCheckReturn();

        Result<Collection<BookItem>> collectionResult = bookController.listBooksByKeyword(keyword);
        if (!collectionResult.isSuccess()) {
            System.out.println(collectionResult.getMsg());
        } else {
            Collection<BookItem> data = collectionResult.getData();
            for (BookItem bookItem : data) {
                System.out.println(bookItem.getName() + "\t" + bookItem.getAuthor() + "\t" + bookItem.getInventory());
            }
        }

        return null;

    }
}
