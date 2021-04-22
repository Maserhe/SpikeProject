package com.maserhe.service;

import com.maserhe.entity.Item;
import com.maserhe.entity.Stock;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.ItemMapper;
import com.maserhe.mapper.StockMapper;
import com.maserhe.service.model.ItemModel;
import com.maserhe.validator.ValidationResult;
import com.maserhe.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * item service
 *
 * @author Maserhe
 * @create 2021-04-21 14:09
 */
@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;


    @Autowired
    private StockMapper stockMapper;


    @Autowired
    private ValidatorImpl validator;


    /**
     *  添加item
     * @param item
     * @return
     * @throws BusinessException
     */
    public int addItem(Item item) throws BusinessException {
        if (item == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);
        return itemMapper.insertSelective(item);
    }

    /**
     * 将ItemModel 转化item
     * @param model
     * @return
     * @throws BusinessException
     */
    private Item convertItemFromItemModel(ItemModel model) throws BusinessException {
        if (model == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);
        ValidationResult result = validator.validate(model);

        // 检验参数
        if (result.isHasError()) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
        Item item = new Item();
        BeanUtils.copyProperties(model, item);
        return item;
    }

    /**
     * 将ItemModel 转化Model
     * @param model
     * @return
     * @throws BusinessException
     */
    private Stock convertStockFromItemModel(ItemModel model) throws BusinessException{
        if (model == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);
        ValidationResult result = validator.validate(model);
        // 检验参数
        if (result.isHasError()) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
        Stock stock = new Stock();
        BeanUtils.copyProperties(model, stock);
        return stock;
    }
    





}
