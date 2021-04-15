package com.maserhe.mapper;

import com.maserhe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 描述:
 * 测试PasswordMapper
 *
 * @author Maserhe
 * @create 2021-04-15 18:57
 */
@SpringBootTest
public class TestPasswordMapper {
    
    @Autowired
    private UserMapper userMapper;
    
    
    @Test
    public void test() {

        User user = new User();
        user.setName("hahah1");
        user.setGender(1);
        user.setAge(12);
        user.setRegisterCode("adfasdf");
        user.setTelephone("13413413241");
        user.setThirdPartyId("adfasdfasdf");

        userMapper.insert(user);
        List<User> users = userMapper.selectAll();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }
    
}
