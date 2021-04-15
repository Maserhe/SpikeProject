package com.maserhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 * 测试
 *
 * @author Maserhe
 * @create 2021-04-13 21:04
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/index")
    public String test() {
        return "hello hello";
    }
}
