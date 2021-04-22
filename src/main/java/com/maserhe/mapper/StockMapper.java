package com.maserhe.mapper;

import com.maserhe.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述:
 * Stock Mapper
 *
 * @author Maserhe
 * @create 2021-04-21 14:08
 */
@Mapper
public interface StockMapper extends tk.mybatis.mapper.common.Mapper<Stock> {
}
