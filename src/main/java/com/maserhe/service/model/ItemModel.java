package com.maserhe.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 描述:
 * 商品的领域模型
 *
 * @author Maserhe
 * @create 2021-04-20 21:16
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemModel {


    private Integer id;

    /**
     * 商品标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 商品价格
     */
    @NotNull
    @Min(value = 0, message = "价格不能为负值")
    private Double price;

    /**
     * 商品库存
     */
    @NotNull
    @Min(value = 0, message = "库存不能为负值")
    private Integer stock;

    /**
     * 商品描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 商品销量
     */
    @NotNull
    @Min(value = 0, message = "销量不能为负值")
    private Integer sales;

    /**
     * 商品的图片
     */
    @NotBlank(message = "图片url不能为空")
    private String image;

}
