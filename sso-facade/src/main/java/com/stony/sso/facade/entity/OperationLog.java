package com.stony.sso.facade.entity;

import com.zhuanche.car.commons.page.PageBean;
import java.io.Serializable;
import java.util.Date;

public class OperationLog extends PageBean implements Serializable {
    /**
     * 
     * operation_log.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 0 登录  
     * operation_log.operation_type
     *
     * @mbggenerated
     */
    private Integer operationType;

    /**
     * 用户ID
     * operation_log.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 操作时间
     * operation_log.operation_time
     *
     * @mbggenerated
     */
    private Date operationTime;

    /**
     * operation_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public OperationLog(){}

    public OperationLog(int operationType,Long userId){
        this.operationType = operationType;
        this.userId =userId;
    }

    /**
     * 
     * operation_log.id
     *
     * @return operation_log.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * operation_log.id
     *
     * @param id operation_log.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 0 登录  
     * operation_log.operation_type
     *
     * @return operation_log.operation_type
     *
     * @mbggenerated
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * 0 登录  
     * operation_log.operation_type
     *
     * @param operationType operation_log.operation_type
     *
     * @mbggenerated
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * 用户ID
     * operation_log.user_id
     *
     * @return operation_log.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * operation_log.user_id
     *
     * @param userId operation_log.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 操作时间
     * operation_log.operation_time
     *
     * @return operation_log.operation_time
     *
     * @mbggenerated
     */
    public Date getOperationTime() {
        return operationTime;
    }

    /**
     * 操作时间
     * operation_log.operation_time
     *
     * @param operationTime operation_log.operation_time
     *
     * @mbggenerated
     */
    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    /**
     * operation_log
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", operationType=").append(operationType);
        sb.append(", userId=").append(userId);
        sb.append(", operationTime=").append(operationTime);
        sb.append("]");
        return sb.toString();
    }
}