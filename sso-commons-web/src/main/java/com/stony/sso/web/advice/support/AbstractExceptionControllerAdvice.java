package com.stony.sso.web.advice.support;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.web.entity.ResponseEntity;
import com.stony.sso.web.constants.ResponseConstant;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/23 </p>
 * <p>Time: 10:02 </p>
 * <p>Version: 1.0 </p>
 * 通用的异常处理
 */
public abstract class AbstractExceptionControllerAdvice {

    public abstract Logger getLogger();

    /**
     * Valid Validated 注解错误
     * @see javax.validation.Valid
     * @see org.springframework.validation.annotation.Validated
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object notValid(MethodArgumentNotValidException e){
        getLogger().error(e.getParameter().getMethod() + "参数校验失败", e);
        ResponseEntity<Map> responseEntity = new ResponseEntity<>();
        responseEntity.setData(new HashMap());
        responseEntity.setResponseCode(ResponseConstant.CODE_PARAMETER_INVALID);
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        boolean first = true;
        StringBuilder sb = new StringBuilder("Validation error(s) [ ");
        for (FieldError fieldError : errors) {
            if(first){
                sb.append(fieldError.getField()).append(" = ").append(fieldError.getDefaultMessage());
                first = false;
            }else {
                sb.append(" ,").append(fieldError.getField()).append(" = ").append(fieldError.getDefaultMessage());
            }
        }
        sb.append("]");
        responseEntity.setResponseMessage(sb.toString());
        responseEntity.setResponseDate(DateUtils.now());
        return responseEntity;
    }

    /**
     * 参数校验错误
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Object illegalArgument(IllegalArgumentException e){
        getLogger().error("参数校验失败", e);
        ResponseEntity<Map> responseEntity = new ResponseEntity<>();
        responseEntity.setData(new HashMap());
        responseEntity.setResponseCode(ResponseConstant.CODE_PARAMETER_INVALID);
        responseEntity.setResponseMessage(e.getMessage());
        responseEntity.setResponseDate(DateUtils.now());
        return responseEntity;
    }

    /**
     * Exception 默认异常返回
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object defaulted(Exception e){
        getLogger().error("其他异常", e);
        ResponseEntity<Map> responseEntity = new ResponseEntity<>();
        responseEntity.setData(new HashMap());
        responseEntity.setResponseCode(ResponseConstant.CODE_OTHER_INVALID);
        if(e.getMessage() == null){
            responseEntity.setResponseMessage(ResponseConstant.MSG_OTHER_INVALID);
        }else{
            responseEntity.setResponseMessage(e.getMessage());
        }
        responseEntity.setResponseDate(DateUtils.now());
        return responseEntity;
    }

}
