package com.maserhe.entity;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 * User类
 *
 * @author Maserhe
 * @create 2021-04-15 18:45
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_info")
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String telephone;
    private String registerCode;
    private String thirdPartyId;


}
