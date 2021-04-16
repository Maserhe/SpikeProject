package com.maserhe.service;

import com.maserhe.entity.Password;
import com.maserhe.entity.User;
import com.maserhe.entity.Vo.UserVo;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.PasswordMapper;
import com.maserhe.mapper.UserMapper;
import com.maserhe.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);


    }

}
