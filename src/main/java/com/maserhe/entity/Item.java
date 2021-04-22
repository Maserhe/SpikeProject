package com.maserhe.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-20 21:30
 */
@Table(name = "item")
public class Item {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank()
    private String title;
    private Double price;
    private String description;
    private Integer sales;
    private String imgUrl;

}
