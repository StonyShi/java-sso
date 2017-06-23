package com.stony.sso.service.service;


import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.OperationLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2016/8/29.
 */
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService{

    private static final Logger logger = LoggerFactory.getLogger(OperationLogServiceImpl.class);

    @javax.annotation.Resource
    OperationLogMapper operationLogMapper;

    @Override
    public void insertOperation(OperationLog log) {
        log.setOperationTime(DateUtils.now());
        logger.debug("operation->>",log);
        operationLogMapper.insert(log);
    }
    @Override
    public List<OperationLog> findLoginTime(OperationLog log) {
        logger.debug("operation->>", log);
        return operationLogMapper.findLoginTime(log);
    }
}
