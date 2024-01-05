package org.example.controller;

import org.example.common.Result;
import org.example.entity.BorrowRecord;

import java.util.List;

/**
 *  for BorrowRecord crud
 */
public interface IBorrowRecordController extends IController{

    Result<List<BorrowRecord>> getByUserAccount(String userAccount);
}
