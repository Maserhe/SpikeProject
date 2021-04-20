package com.maserhe.validator;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-20 20:32
 */
public class ValidationResult {

    private boolean hasError = false;

    private Map<String, String> getErrorMsg = new HashMap<>();

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getGetErrorMsg() {
        return getErrorMsg;
    }

    public void setGetErrorMsg(Map<String, String> getErrorMsg) {
        this.getErrorMsg = getErrorMsg;
    }

    /**
     * 返回格式化的 字符
     * @return
     */
    public String getErrorMsg() {
        return StringUtils.join(getErrorMsg, ",");
    }

}
