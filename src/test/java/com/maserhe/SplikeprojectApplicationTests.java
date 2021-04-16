package com.maserhe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class SplikeprojectApplicationTests {

    @Test
    void contextLoads() {


        int random = (int) (100000 + Math.random() * 100000);

        System.out.println(random);;
    }

}
