package com.maserhe.error;

/**
 * 描述: 包装器业务异常类实现
 *  包装器模式，
 * @author Maserhe
 * @create 2021-04-15 21:11
 */
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    /**
     * 直接接受 异常
     * @param commonError
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String msg) {
        super();
        commonError.setErrMsg(msg);
        this.commonError= commonError;
    }



    @Override
    public int getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
