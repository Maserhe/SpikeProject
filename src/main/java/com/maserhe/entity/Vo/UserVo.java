package com.maserhe.entity.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 * 视图实体类
 *
 * @author Maserhe
 * @create 2021-04-15 20:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String telephone;

}
