package com.tian.utils.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ projectName: Springboot
 * @ package: com.tian.utils
 * @ className: ResultResponse
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/22 10:50
 * @ version: 1.0
 */
@Data
@NoArgsConstructor
public class ResultResponse<T> {
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //数据
    private T data;

    //自定义返回的成功结果
    public ResultResponse(T data) {
        this.code = 200;
        this.data = data;
    }

    //自定义异常返回的失败结果
    public ResultResponse(String message) {
        this.code = 400;
        this.msg = message;
    }

    //自定义返回的成功结果
    public static <T> ResultResponse success(T data) {
        ResultResponse result = new ResultResponse();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    //自定义异常返回的失败结果
    public static ResultResponse fail(String message) {
        ResultResponse result = new ResultResponse();
        result.setCode(400);
        result.setMsg(message);
        return result;
    }

    //自定义异常返回的结果
    public static ResultResponse defineError(DefinitionException de){
        ResultResponse result = new ResultResponse();
        result.setCode(de.getErrorCode());
        result.setMsg(de.getErrorMsg());
        return result;
    }
    //其他异常处理方法返回的结果
    public static ResultResponse otherError(ErrorEnum errorEnum){
        ResultResponse result = new ResultResponse();
        result.setMsg(errorEnum.getErrorMsg());
        result.setCode(errorEnum.getErrorCode());
        return result;
    }

}
