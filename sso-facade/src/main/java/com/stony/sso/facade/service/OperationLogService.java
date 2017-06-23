package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.OperationLog;

import java.util.List;

/**
 * Created by user on 2016/8/29.
 * 操作记录
 */
public interface OperationLogService {

    void insertOperation(OperationLog log);

    List<OperationLog> findLoginTime(OperationLog log);
}
