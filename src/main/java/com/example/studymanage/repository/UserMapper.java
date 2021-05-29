package com.example.studymanage.repository;

import com.example.studymanage.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * @Description:根据id查询用户信息
     * @Author: lgzhang
     * @Date:   2021/5/18 16:53
     * @Param:  [id]
     * @Return: com.example.studymanage.domain.User
     */
    @Select("select * from userinfo where id=#{id}")
    User queryUserById(String id);

    /**
     * @Description:根据id校验密码是否正确
     * @Author: lgzhang
     * @Date:   2021/5/21 21:10
     * @Param: [id,password]
     * @Return: int
     */
    @Select("select count(1) from userinfo where id=#{id} and password=#{password}")
    int checkPwd(String id,String password);

    /**
     * @Description:
     * @Author: lgzhang
     * @Date:   2021/5/23 15:11
     * @Param:  [id, username, password, phone, age, identity]
     * @Return: int
     */
    @Insert("insert into userinfo values(#{id},#{username},#{password},#{phone}," +
            "#{age},#{identity})")
    int addUser(String id,String username,String password,String phone,String age,String identity);
}
