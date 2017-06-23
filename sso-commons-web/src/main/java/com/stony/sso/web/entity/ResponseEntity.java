package com.stony.sso.web.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.JacksonUtil;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Stony Created Date : 2016/4/21  10:58
 */
public class ResponseEntity<T> implements Serializable{

    private T data;
    private Date responseDate;
    private int responseCode;
    private String responseMessage = "";

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     *
     * @return 返回json字符串，null值不显示
     */
    public String toJsonString(){
        return JacksonUtil.toJson(this);
    }

    @Override
    public String toString() {
        return "ResponseEntity[" +
                " responseCode = " + responseCode +
                " ,responseMessage = " + responseMessage +
                " ,responseDate = " + responseDate +
                " ,data = " + data +
                "]";
    }
}
