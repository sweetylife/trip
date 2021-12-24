package com.tian.utils.result;

/**
 * @ projectName: Springboot
 * @ package: com.tian.utils
 * @ className: DefinitionException
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/22 11:18
 * @ version: 1.0
 */
public class DefinitionException extends RuntimeException{

    protected Integer errorCode;
    protected String errorMsg;

    public DefinitionException(){

    }
    public DefinitionException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public DefinitionException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getErrorCode();
        this.errorMsg = errorEnum.getErrorMsg();
    }
    public DefinitionException(String errorMsg) {
        this.errorCode = ErrorEnum.ERROR_MESSAGE.getErrorCode();
        this.errorMsg = errorMsg;
    }
    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
