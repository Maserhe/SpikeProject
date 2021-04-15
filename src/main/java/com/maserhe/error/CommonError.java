package com.maserhe.error;

/**
 * 描述:
 * 通用错误
 *
 * @author Maserhe
 * @create 2021-04-15 21:00
 */
public interface CommonError {

    int getErrorCode();
    String getErrorMsg();
    CommonError setErrMsg(String errMsg);

}
