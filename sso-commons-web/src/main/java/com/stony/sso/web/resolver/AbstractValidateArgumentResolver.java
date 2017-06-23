package com.stony.sso.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/13 </p>
 * <p>Time: 18:08 </p>
 * <p>Version: 1.0 </p>
 *  Valid 注解校验器
 *  @see javax.validation.Valid
 */
public abstract class AbstractValidateArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object arg = doResolveArgument(parameter, mavContainer, webRequest, binderFactory);
        if (arg != null) {
            String name = parameter.getParameterName();
            WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
            validate(binder, parameter);
        }
        return arg;
    }

    /**
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    public abstract Object doResolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception;


    private void validate(WebDataBinder binder, MethodParameter parameter) throws MethodArgumentNotValidException {
        Annotation[] annotations = parameter.getParameterAnnotations();
        for (Annotation annot : annotations) {
            if (annot.annotationType().getSimpleName().startsWith("Valid")) {
                Object hints = AnnotationUtils.getValue(annot);
                binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
                BindingResult bindingResult = binder.getBindingResult();
                if (bindingResult.hasErrors()) {
                    if (isBindExceptionRequired(binder, parameter)) {
                        throw new MethodArgumentNotValidException(parameter, bindingResult);
                    }
                }
            }
        }
    }

    /**
     * Whether to raise a {@link MethodArgumentNotValidException} on validation errors.
     * @param binder the data binder used to perform data binding
     * @param parameter the method argument
     * @return {@code true} if the next method argument is not of type {@link Errors}.
     */
    private boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
        int i = parameter.getParameterIndex();
        Class<?>[] paramTypes = parameter.getMethod().getParameterTypes();
        boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));

        return !hasBindingResult;
    }

}
