package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Password {

    private Integer id;
    private String encryptPassword;
    private Integer userId;

}
