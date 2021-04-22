package com.maserhe.service;

import com.maserhe.entity.Stock;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-21 14:10
 */
@Service
public class StockService {

    @Autowired
    private StockMapper stockMapper;

    /**
     * 添加库存
     * @param stock
     * @return
     * @throws BusinessException
     */
    public int addStock(Stock stock) throws BusinessException {
        if (stock == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);
        return stockMapper.insertSelective(stock);
    }



}
