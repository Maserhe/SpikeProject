package com.maserhe.mapper;

import com.maserhe.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述:
 * item Mapper
 *
 * @author Maserhe
 * @create 2021-04-21 14:05
 */
@Mapper
public interface ItemMapper extends tk.mybatis.mapper.common.Mapper<Item>{

    public List<Item> listItem();

    public int decreaseItem(@Param("itemId") Integer itemId, @Param("amount") Integer amount);

}