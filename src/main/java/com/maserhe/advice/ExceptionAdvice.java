package com.maserhe.advice;

import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 描述:
 * 统一的异常处理
 *
 * @author Maserhe
 * @create 2021-04-15 22:06
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    /**
     * 通用的 异常处理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e) {

        CommonReturnType commonReturnType = new CommonReturnType();
        HashMap<String, Object> hashMap = new HashMap<>();
        commonReturnType.setStatus("fail");

        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            hashMap.put("errCode", businessException.getErrorCode());
            hashMap.put("errMsg", businessException.getErrorMsg());
        } else {
            hashMap.put("errorCode", EmBusinesssError.UNKNOWN_ERROR.getErrorCode());
            hashMap.put("errorMsg", EmBusinesssError.UNKNOWN_ERROR.getErrorMsg());
        }

        commonReturnType.setData(hashMap);
        return commonReturnType;
    }
}
