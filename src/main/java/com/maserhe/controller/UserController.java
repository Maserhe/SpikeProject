package com.maserhe.controller;


import com.maserhe.entity.Password;
import com.maserhe.entity.Vo.UserVo;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.response.CommonReturnType;
import com.maserhe.service.PasswordService;
import com.maserhe.service.UserService;
import com.maserhe.service.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.mk.CollationData_mk;

import javax.servlet.http.HttpServletRequest;


/**
 * 描述:
 * 用户Controller
 *
 * @author Maserhe
 * @create 2021-04-15 19:59
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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

    private Password passwordFromUserModel(UserModel userModel) {
        if (userModel == null) return null;
        Password password = new Password();
        password.setUserId(userModel.getId());
        password.setEncryptPassword(userModel.getEncryptPassword());
        return password;
    }

    /**
     * 获取短信接口的
     * @param phone
     * @return
     */
    @PostMapping(value = "/getotp", consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam(name = "telephone") String phone, HttpServletRequest request) {

        // 生成Otp 验证码
        int random = (int) (100000 + Math.random() * 100000);
        // 将otp验证同对应的 用户手机号关联。
        String otpCode = String.valueOf(random);
        // 将验证码发送给用户
        // System.out.println(otpCode);
        request.getSession().setAttribute(phone, otpCode);
        // System.out.println(request.getSession().getAttribute(phone));

        // 记录一下日志
        logger.info("手机号:" + phone + " 验证码 " + random);
        return CommonReturnType.create(otpCode);
    }

    /**
     * 用户注册接口
     * @param name
     * @param gender
     * @param age
     * @param telephone
     * @return
     */

    @PostMapping(path = "/register", consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "telephone") String telephone, HttpServletRequest request) throws BusinessException {
        // 获取验证码
        String attribute = (String) request.getSession().getAttribute(telephone);
        if (attribute == null || telephone == null || !attribute.equals(otpCode)) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "验证码不正确");

        // 用户注册
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setTelephone(telephone);

        userModel.setEncryptPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userModel.setRegisterCode("byPhone");
        userService.register(userModel);
        return CommonReturnType.create(userVoFromUserModel(userModel));
    }

    @PostMapping(value = "/login", consumes = {"application/x-www-form-urlencoded"})
    public CommonReturnType login(@RequestParam("username") String username, @RequestParam("password") String password) throws BusinessException {
        // 参数校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, "用户名或者密码为空");



        return CommonReturnType.create(null);
    }


}
