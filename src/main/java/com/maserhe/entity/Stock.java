package com.maserhe.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 描述:
 * 库存的实体类
 *
 * @author Maserhe
 * @create 2021-04-21 13:59
 */
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int stock;

}
