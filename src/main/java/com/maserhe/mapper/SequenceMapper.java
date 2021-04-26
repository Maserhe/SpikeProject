package com.maserhe.mapper;

import com.maserhe.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 描述:
 * SequenceMapper
 *
 * @author Maserhe
 * @create 2021-04-26 20:17
 */
@Mapper
public interface SequenceMapper extends tk.mybatis.mapper.common.Mapper<Sequence>{

    Sequence getSequenceByName(@Param("name") String name);

}
