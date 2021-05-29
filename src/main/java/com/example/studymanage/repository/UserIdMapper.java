package com.example.studymanage.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserIdMapper {
    /**
     * @Description:获取用户id序列
     * @Author: lgzhang
     * @Date:   2021/5/23 15:32
     * @Param:  []
     * @Return: java.lang.String
     */
    @Select("select seq_userid.nextval from dual")
    String getUserId();

}
