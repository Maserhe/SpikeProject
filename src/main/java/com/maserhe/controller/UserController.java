package com.maserhe.controller;

import com.maserhe.entity.Vo.UserVo;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.response.CommonReturnType;
import com.maserhe.service.UserService;
import com.maserhe.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 描述:
 * 用户Controller
 *
 * @author Maserhe
 * @create 2021-04-15 19:59
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 返回相应的view
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel model = userService.getUserById(id);

        if (model == null) throw new BusinessException(EmBusinesssError.USER_NOT_EXIST);

        return CommonReturnType.create(userVoFromUserModel(model));
    }

    /**
     * 将UserModel 转化未 userVO
     * @param userModel
     * @return
     */
    private UserVo userVoFromUserModel(UserModel userModel) {
        if (userModel == null) return null;
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel, userVo);
        return userVo;
    }





}
