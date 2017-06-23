package com.stony.sso.service.mapper;


import com.stony.sso.facade.entity.OperationLog;

import java.util.List;

public interface OperationLogMapper {
    /**
     * operation_log
     *
     * @mbggenerated
     */
    int insert(OperationLog record);
    /**
     * operation_log
     *
     * @mbggenerated
     */
    OperationLog selectByPrimaryKey(Long id);
    /**
     * operation_log
     *
     * @mbggenerated
     */
    List<OperationLog> findLoginTime(OperationLog log);
}