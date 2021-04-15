package com.maserhe.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 统一的返回
 *
 * @author Maserhe
 * @create 2021-04-15 20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonReturnType {
    /**
     * 返回 success data携带相应的数据
     *     fail  data携带错误消息
     */
    private String status;

    /**
     * 正确
     */
    private Object data;

    /**
     * 正确结果返回
     * @param result
     * @return
     */
    public static CommonReturnType create(Object result) {
        return create("success", result);
    }


    public static CommonReturnType create(String status, Object result) {
        return new CommonReturnType(status, result);
    }

}
