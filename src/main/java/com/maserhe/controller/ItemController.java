package com.maserhe.controller;

import com.maserhe.entity.Vo.ItemVo;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.response.CommonReturnType;
import com.maserhe.service.ItemService;
import com.maserhe.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-04-21 14:12
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/addItem", consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "price") Double price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "sales") Integer sales,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        ItemModel model = new ItemModel();
        model.setTitle(title);
        model.setDescription(description);
        model.setPrice(price);
        model.setStock(stock);
        model.setSales(sales);
        model.setImgUrl(imgUrl);

        if (model == null ) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "库存参数错误");
        ItemModel itemModel = itemService.createItemModel(model);
        ItemVo vo = new ItemVo();
        BeanUtils.copyProperties(itemModel, vo);

        return CommonReturnType.create(vo);
    }

    /**
     * 查询具体一个 item
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType getItemModel(@RequestParam("id") Integer id) {
       return CommonReturnType.create(itemService.getItemModelById(id));
    }


    @GetMapping("/listItem")
    @ResponseBody
    public CommonReturnType getItemListModel(){
        return CommonReturnType.create(itemService.getItemModelList());
    }






}
