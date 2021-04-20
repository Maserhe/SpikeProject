package com.maserhe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "性别不能不填写")
    @Min(value = 0, message = "只有零或者1")
    @Max(value = 1, message = "只有零或者1")
    private Integer gender;

    @NotNull(message = "年龄")
    @Min(value = 0, message = "年龄不能小于0")
    @Max(value = 150, message = "年龄不能大于150")
    private Integer age;


    private String telephone;
    private String registerCode;
    private String thirdPartyId;

    private String encryptPassword;
}
