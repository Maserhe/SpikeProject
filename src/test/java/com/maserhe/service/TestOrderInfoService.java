package com.maserhe.service;

import com.maserhe.entity.OrderInfo;
import com.maserhe.mapper.OrderInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述:
 * 测试订单的业务情况
 *
 * @author Maserhe
 * @create 2021-04-25 20:43
 */
@SpringBootTest
public class TestOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderService orderService;

    @Test
    public void test() {

        OrderInfo orderInfo = new OrderInfo();

        orderInfo.setId("d133413241324");
        orderInfo.setItemId(1);
        orderInfo.setOrderPrice(11.2);
        orderInfo.setUserId(12);
        orderInfo.setAmount(122);
        orderInfoMapper.insert(orderInfo);

    }

    @Test
    public void test1() {

        System.out.println(orderService.generateOrderNo());
    }
}
