package com.tian.utils.result;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ projectName: Springboot
 * @ package: com.tian.utils.result
 * @ className: ProjectExceptionAdvice
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/22 11:41
 * @ version: 1.0
 */

//作为springMvc的异常处理器

@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有的异常信息
    //处理自定义异常
    @ExceptionHandler(value = DefinitionException.class)
    public ResultResponse bizExceptionHandler(DefinitionException ex){
        System.out.println("拦截：处理自定义异常");
        return ResultResponse.defineError(ex);
    }

    //处理其他异常
    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptionHandler( Exception e) {
        e.printStackTrace();
        System.out.println("拦截：处理系统异常");
        return ResultResponse.otherError(ErrorEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException me){
        String field = me.getBindingResult().getFieldError().getField();
        String message = me.getBindingResult().getFieldError().getDefaultMessage();
        return ResultResponse.defineError(new DefinitionException(ErrorEnum.NO_PARAM.getErrorCode(),field+message));
    }

    //请求方式不符合的异常拦截
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException me){
        return ResultResponse.defineError(new DefinitionException("不支持的请求方式"));
    }
}
