package com.maserhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 * 加入sequence
 *
 * @author Maserhe
 * @create 2021-04-26 20:15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sequence_info")
public class Sequence {

    @Id
    private String name;
    private Integer currentValue;
    private Integer step;

}
