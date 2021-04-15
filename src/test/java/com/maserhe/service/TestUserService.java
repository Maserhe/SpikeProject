package com.maserhe.service;

import com.maserhe.service.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 描述:
 * 测试service
 *
 * @author Maserhe
 * @create 2021-04-15 19:49
 */
@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {

        UserModel userById = userService.getUserById(1);

        System.out.println(userById);

    }
}
