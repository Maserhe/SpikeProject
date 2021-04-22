package com.maserhe.service;

import com.maserhe.entity.Password;
import com.maserhe.entity.User;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.PasswordMapper;
import com.maserhe.mapper.UserMapper;
import com.maserhe.service.model.UserModel;
import com.maserhe.validator.ValidationResult;
import com.maserhe.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;


/**
 * 描述:
 *  用户服务
 * @author Maserhe
 * @create 2021-04-15 19:25
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordMapper passwordMapper;

    @Autowired
    private ValidatorImpl validator;

    /**
     * 获取用户的Model
     * @param id
     * @return
     */
    public UserModel getUserById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null ) return null;
        Example example = new Example(Password.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", user.getId());
        Password password = passwordMapper.selectOneByExample(example);
        return convertToUserModel(user, password);
    }

    /**
     * 转化成 UserModel 对象
     * @param user
     * @param password
     * @return
     */
    public UserModel convertToUserModel(User user, Password password) {
        if (user == null || password == null) return null;
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        userModel.setEncryptPassword(password.getEncryptPassword());

        return userModel;
    }

    /**
     *  用户注册。传入 userModel
     * @param userModel
     * @return
     * @throws BusinessException
     */
    public int register(UserModel userModel) throws BusinessException {
        if (userModel == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);

        ValidationResult result = validator.validate(userModel);
        // System.out.println(result.getGetErrorMsg());

        if (result.isHasError()) {
            throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR, result.getErrorMsg());
        }

        User user = new User();
        Password password = new Password();

        // UserModel 转化为 user
        BeanUtils.copyProperties(userModel, user);
        BeanUtils.copyProperties(userModel, password);

        userMapper.insertSelective(user);

        password.setUserId(user.getId());
        return passwordMapper.insertSelective(password);
    }

}
