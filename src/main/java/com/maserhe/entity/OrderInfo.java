package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 * 订单信息
 *
 * @author Maserhe
 * @create 2021-04-25 20:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_info")
public class OrderInfo {

    @Id
    private String id;
    private Integer userId;
    private Integer itemId;
    private Integer amount;
    private Double orderPrice;

}
