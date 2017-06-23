package com.stony.sso.facade.entity;

import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.page.PageBean;

import java.io.Serializable;
import java.util.Date;

@Optimizer
public class GlobalVariable extends PageBean implements Serializable {
    /**
     * 
     * global_variable.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 字段
     * global_variable.field
     *
     * @mbggenerated
     */
    private String field;

    /**
     * 值
     * global_variable.value
     *
     * @mbggenerated
     */
    private String value;

    /**
     * 描述
     * global_variable.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 1 有效
     * global_variable.available
     *
     * @mbggenerated
     */
    private Byte available;

    /**
     * 插入时间
     * global_variable.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * global_variable.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * global_variable
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * global_variable.id
     *
     * @return global_variable.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * global_variable.id
     *
     * @param id global_variable.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 字段
     * global_variable.field
     *
     * @return global_variable.field
     *
     * @mbggenerated
     */
    public String getField() {
        return field;
    }

    /**
     * 字段
     * global_variable.field
     *
     * @param field global_variable.field
     *
     * @mbggenerated
     */
    public void setField(String field) {
        this.field = field == null ? null : field.trim();
    }

    /**
     * 值
     * global_variable.value
     *
     * @return global_variable.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * 值
     * global_variable.value
     *
     * @param value global_variable.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 描述
     * global_variable.description
     *
     * @return global_variable.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * global_variable.description
     *
     * @param description global_variable.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 1 有效
     * global_variable.available
     *
     * @return global_variable.available
     *
     * @mbggenerated
     */
    public Byte getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * global_variable.available
     *
     * @param available global_variable.available
     *
     * @mbggenerated
     */
    public void setAvailable(Byte available) {
        this.available = available;
    }

    /**
     * 插入时间
     * global_variable.insert_date
     *
     * @return global_variable.insert_date
     *
     * @mbggenerated
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * global_variable.insert_date
     *
     * @param insertDate global_variable.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * global_variable.update_date
     *
     * @return global_variable.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * global_variable.update_date
     *
     * @param updateDate global_variable.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * global_variable
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
        sb.append(", field=").append(field);
        sb.append(", value=").append(value);
        sb.append(", description=").append(description);
        sb.append(", available=").append(available);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}