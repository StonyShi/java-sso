package com.stony.sso.server.manger.controller;

import com.stony.sso.web.advice.support.AbstractExceptionControllerAdvice;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 11:57 </p>
 * <p>Version: 1.0 </p>
 */
@ControllerAdvice
public class ExceptionControllerAdvice extends AbstractExceptionControllerAdvice{

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("error/unauthorized");
        return mv;
    }


    @Override
    public Logger getLogger() {
        return ExceptionControllerAdvice.logger;
    }
}
