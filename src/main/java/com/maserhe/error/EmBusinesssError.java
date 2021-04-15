package com.maserhe.error;

/**
 * 描述:
 * 错误的枚举
 *
 * @author Maserhe
 * @create 2021-04-15 21:02
 */
public enum EmBusinesssError implements CommonError{


    PARAMETER_VALIDATION_ERROR(00001, "参数不合法"),
    UNKNOWN_ERROR(00002,"未知错误"),
    USER_NOT_EXIST(10001, "用户不存在");


    private int errorCode;
    private String errMsg;

    EmBusinesssError(int errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }


    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errMsg;
    }

    /**
     * 用于改动 error 消息
     * @param errMsg
     * @return
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
