package com.maserhe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 交易模型
 *
 * @author Maserhe
 * @create 2021-04-25 20:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    /**
     * 有一定 格式的 订单序列号
     */
    private String id;

    private Integer userId;

    private Integer itemId;

    private Integer amount;

    private Double orderPrice;


}
