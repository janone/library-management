package org.example.view;

import org.example.common.ControllerFactory;
import org.example.controller.IBookController;
import org.example.entity.User;

public class ReturnBooksView implements View{

    private IBookController bookController = ControllerFactory.getBean(IBookController.class);

    public void show(User user) {
    }

}
