package com.maserhe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * 描述:
 * 用户Model
 *
 * @author Maserhe
 * @create 2021-04-15 19:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String telephone;
    private String registerCode;
    private String thirdPartyId;

    private String encryptPassword;
}
