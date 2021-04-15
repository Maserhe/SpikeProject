package com.maserhe.mapper;

import com.maserhe.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述:
 * user 的mapper
 *
 * @author Maserhe
 * @create 2021-04-15 18:54
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
}
