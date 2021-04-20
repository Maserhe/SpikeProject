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
 * 密码表
 *
 * @author Maserhe
 * @create 2021-04-15 18:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String encryptPassword;
    private Integer userId;

}
