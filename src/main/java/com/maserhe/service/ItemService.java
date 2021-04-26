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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 添加model
     * @param model
     * @return
     */
    @Transactional
    public ItemModel createItemModel(ItemModel model) throws BusinessException {
        // 校验入参
        if (model == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "参数为空");
        ValidationResult result = validator.validate(model);
        // 检验参数
        if (result.isHasError()) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
        // 转化为 do
        Stock stock = convertStockFromItemModel(model);
        Item item = convertItemFromItemModel(model);
        // 写入数据库
        itemMapper.insertSelective(item);
        stock.setItemId(item.getId());
        stockMapper.insertSelective(stock);

        model.setId(item.getId());
        // 返回创建完成的对象
        return model;
    }

    /**
     * 获取领域模型
     * @param id
     * @return
     */
    public ItemModel getItemModelById(Integer id) {
        Item item = itemMapper.selectByPrimaryKey(id);
        if (item == null) return null;
        Example example = new Example(Stock.class);
        example.createCriteria().andEqualTo("itemId", id);
        Stock stock = stockMapper.selectOneByExample(example);
        // 将两个对象合二为一
        ItemModel model = new ItemModel();
        BeanUtils.copyProperties(item, model);
        model.setStock(stock.getStock());
        return model;
    }


    public List<ItemModel> getItemModelList() {

        // 先查询所有 item
        List<Item> itemList = itemMapper.listItem();
        List<ItemModel> ans = itemList.stream().map(item -> {
            Example example = new Example(Stock.class);
            example.createCriteria().andEqualTo("itemId", item.getId());
            Stock stock = stockMapper.selectOneByExample(example);
            return convertToItemModel(item, stock);
        }).collect(Collectors.toList());
        return ans;

    }

    /**
     * 转化为 model
     * @param item
     * @param stock
     * @return
     */
    public ItemModel convertToItemModel(Item item, Stock stock) {
        if (item == null || stock == null) return null;
        ItemModel model = new ItemModel();
        BeanUtils.copyProperties(item, model);
        model.setStock(stock.getStock());
        return model;
    }

    /**
     * 减去库存
     * @param itemId
     * @param amount
     * @return
     */
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount ) {
        int row = itemMapper.decreaseItem(itemId, amount);
        return row > 0? true: false;
    }


}
