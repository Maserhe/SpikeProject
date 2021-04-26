package com.maserhe.service;

import com.maserhe.entity.OrderInfo;
import com.maserhe.entity.Sequence;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.ItemMapper;
import com.maserhe.mapper.OrderInfoMapper;
import com.maserhe.mapper.SequenceMapper;
import com.maserhe.service.model.ItemModel;
import com.maserhe.service.model.OrderModel;
import com.maserhe.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GenerationType;
import javax.sound.midi.Sequencer;
import javax.swing.plaf.nimbus.NimbusStyle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 描述:
 * 订单service
 *
 * @author Maserhe
 * @create 2021-04-25 20:52
 */
@Service
public class OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        // 1, 校验下单状态，下单的商品是否存在， 用户是否合法， 购买数量是否正确

        ItemModel item = itemService.getItemModelById(itemId);
        if (item == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "参数不正确");

        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "参数不正确");
        if (amount <= 0 || amount > 99 ) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "参数不正确");
        // 2, 落单减库存， 2，支付减去库存(没有支付锁）

        boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "库存不足");
        // 3，订单入库
        OrderModel orderModel = new OrderModel();

        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setOrderPrice(item.getPrice());
        orderModel.setOrderPrice(item.getPrice() * amount);

        orderModel.setId(generateOrderNo());
        // 4, 返回前端
        OrderInfo orderInfo = convertFromModel(orderModel);
        orderInfoMapper.insertSelective(orderInfo);
        return orderModel;

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo() {
        StringBuilder sb = new StringBuilder();
        // 订单有 16位
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern(now.format(DateTimeFormatter.ISO_DATE))).replace("-", "");
        // 前8位为时间信息
        sb.append(format);
        // 中间6位 为自增序列
        // 获取当前sequence

        Sequence sequence = sequenceMapper.getSequenceByName("order_info");
        Integer currentValue = sequence.getCurrentValue();
        // 当前值 加上 步长
        sequence.setCurrentValue(currentValue + sequence.getStep());
        // 更新
        sequenceMapper.updateByPrimaryKeySelective(sequence);

        String sequenceStr = String.valueOf(currentValue);
        for (int i = 0; i < 6 - sequenceStr.length(); i ++ ) {
            sb.append("0"); // 凑足6位
        }
        sb.append(sequenceStr);
        // 后两位 为分库分表 暂时写死
        sb.append("00");
        return sb.toString();
    }


    private OrderInfo convertFromModel(OrderModel model) throws BusinessException {
        if (model == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "参数不正确");
        OrderInfo ans = new OrderInfo();
        BeanUtils.copyProperties(model, ans);
        return ans;
    }

}
