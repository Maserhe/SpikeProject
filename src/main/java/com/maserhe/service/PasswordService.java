package com.maserhe.service;

import com.maserhe.entity.Password;
import com.maserhe.error.BusinessException;
import com.maserhe.error.EmBusinesssError;
import com.maserhe.mapper.PasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *  密码Service
 * @author Maserhe
 * @create 2021-04-19 16:21
 */
@Service
public class PasswordService {


    @Autowired
    private PasswordMapper passwordMapper;

    public int register(Password password) throws BusinessException {
        if (password == null || password.getUserId() == null || password.getEncryptPassword() == null) throw new BusinessException(EmBusinesssError.PARAMETER_VALIDATION_ERROR);
        return passwordMapper.insertSelective(password);
    }


}
