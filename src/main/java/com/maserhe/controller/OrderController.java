package com.maserhe.controller;

import com.maserhe.error.BusinessException;
import com.maserhe.response.CommonReturnType;
import com.maserhe.service.OrderService;
import com.maserhe.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述:
 * 下单控制
 *
 * @author Maserhe
 * @create 2021-04-26 21:43
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/createorder")
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount) throws BusinessException {
        OrderModel order = orderService.createOrder(null, itemId, amount);
        return CommonReturnType.create(order);
    }

}
