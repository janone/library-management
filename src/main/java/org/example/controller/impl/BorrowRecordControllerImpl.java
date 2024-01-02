package org.example.controller.impl;

import org.example.annotation.AutoWiredField;
import org.example.common.Result;
import org.example.controller.IBorrowRecordController;
import org.example.entity.BorrowRecord;
import org.example.service.BorrowRecordService;

import java.util.List;

public class BorrowRecordControllerImpl implements IBorrowRecordController {
    @AutoWiredField
    private BorrowRecordService borrowRecordService;


    @Override
    public Result<List<BorrowRecord>> getByUserAccount(String userAccount) {
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setUserAccount(userAccount);
        return Result.successWithData(borrowRecordService.list(borrowRecord));
    }
}
