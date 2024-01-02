package org.example.controller.impl;

import org.example.controller.IBorrowRecordController;
import org.example.service.BorrowRecordService;

public class BorrowRecordControllerImpl implements IBorrowRecordController {

    private BorrowRecordService borrowRecordService = new BorrowRecordService();
    @Override
    public Object getServiceBean() {
        return this.borrowRecordService;
    }

    @Override
    public Object getDaoBean() {
        return this.borrowRecordService.getDao();
    }
}
