package com.example.studymanage.service;

import com.example.studymanage.domain.User;
import com.example.studymanage.repository.UserIdMapper;
import com.example.studymanage.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserIdMapper userIdMapper;

    /**
     * @Description:根据id查询用户
     * @Author: lgzhang
     * @Date:   2021/5/23 15:12
     * @Param:  [id]
     * @Return: com.example.studymanage.domain.User
     */
    public User queryUserById(String id){
        log.info("本次查询用户id为："+id);
        User user = userMapper.queryUserById(id);
        return user;
    }

    /**
     * @Description:校验用户密码
     * @Author: lgzhang
     * @Date:   2021/5/23 15:12
     * @Param:  [id, password]
     * @Return: java.lang.Boolean
     */
    public Boolean checkPwd(String id,String password){
        //log.info("校验登录密码");
        int flag = userMapper.checkPwd(id,password);
        if (flag==1){
            log.info("密码校验成功");
            return true;
        }else {
            log.info("密码校验失败");
            return false;
        }
    }

    /**
     * @Description:注册用户
     * @Author: lgzhang
     * @Date:   2021/5/23 15:33
     * @Param:  [user]
     * @Return: java.lang.Boolean
     */
    public String addUser(User user){
        log.info("添加新用户");
        String id = userIdMapper.getUserId();//用户id，调用创建的userid序列查询id
        log.info("本次创建用户id："+id);
        String identity= "USER";//用户身份，默认普通用户：USER；创建管理员时，先创建普通用户，再附加管理员权限：ADMIN
        log.info(String.valueOf(user));
        int flag=userMapper.addUser(id,user.getUsername(),user.getPassword(),user.getPhone(),user.getAge(),identity);
        if (flag==1){
            log.info("用户添加成功");
            return id;
        }else{
            log.info("用户添加失败");
            return "false";
        }
    }
}
