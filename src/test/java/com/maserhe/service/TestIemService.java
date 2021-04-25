package com.maserhe.service;

import com.maserhe.entity.Item;
import com.maserhe.error.BusinessException;
import com.maserhe.service.model.ItemModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 描述:
 * 测试 库存
 *
 * @author Maserhe
 * @create 2021-04-25 15:59
 */
@SpringBootTest
public class TestIemService {

    @Autowired
    private ItemService itemService;


    @Test
    public void test() throws BusinessException {
        /*
        ItemModel model = new ItemModel();
        model.setImgUrl("adfadf");
        model.setSales(12);
        model.setTitle("adsfa");
        model.setPrice(12.15);
        model.setDescription("描述");
        model.setStock(131);
        itemService.createItemModel(model);
         */

        ItemModel itemModelById = itemService.getItemModelById(30);
        System.out.println(itemModelById);

        List<ItemModel> itemModelList = itemService.getItemModelList();
        itemModelList.forEach(System.out::println);


    }
}
