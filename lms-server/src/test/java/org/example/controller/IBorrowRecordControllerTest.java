package org.example.controller;

import org.example.common.BeanFactory;
import org.example.controller.impl.BorrowRecordControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IBorrowRecordControllerTest {

    private IBorrowRecordController controller = BeanFactory.getBean(BorrowRecordControllerImpl.class);
    @BeforeEach
    void setUp() {
    }

    @Test
    void getByUserAccount() {
    }
}