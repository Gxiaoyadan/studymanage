package com.example.studymanage.controller;

import com.example.studymanage.domain.User;
import com.example.studymanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //@Resource
    //private RedisTemplate redisTemplate;
    @Resource
    private Jedis jedis;
    /**
     * @Description:根据用户id查询用户信息（如果上送id为null，取session中存储的id进行查询）
     * @Author: lgzhang
     * @Date:   2021/5/23 15:34
     * @Param:  [request]
     * @Return: java.lang.String
     */
    @RequestMapping(value = "/queryUserById",method = RequestMethod.POST)
    public User getUserNameById(@RequestParam(value = "id",required=false) String id,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user =  new User();
        String userId=id;

        log.info("上送id:"+userId);
        if(userId==null||"null".equals(userId)||"".equals(userId)){
            log.info("上送id为空，开始读取session中id");
            userId=(String) session.getAttribute("id");
            if (userId==null||"".equals(userId)){
                log.info("session中id也为空");
                log.info("返回的user："+String.valueOf(user));
                return user;
            }else {
                log.info("session中id存在");
            }
        }else

        log.info("id存在，开始安全性校验");
        String redID = jedis.get(userId);//获取redis存放的id
        if(redID!=null){
            log.info("redis存在该记录，开始校验");
            if(redID.equals(session.getId())){
                log.info("redis校验通过，开始查询");
            }else{
                log.info("redis校验失败，危险登录");
                return user;
            }
        }else {
            log.info("redis不存在该用户id，危险登录");
            return user;
        }


        user = userService.queryUserById(userId);
        if (user==null){
            log.info("该用户不存在");
        }else {
            user.setPassword(null);//屏蔽返回的敏感信息
            log.info("查询该用户为："+user.getUsername());
        }
        log.info("返回的user："+String.valueOf(user));
        return user;
    }

    /**
     * @Description:注册用户
     * @Author: lgzhang
     * @Date:   2021/5/23 15:47
     * @Param:  [user]
     * @Return: java.lang.Boolean
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerUser(@RequestBody User user){
        String regResult = userService.addUser(user);//注册成功返回id，失败返回"false"
        if (!"false".equals(regResult)){
            log.info("注册成功，用户id："+regResult);
        }else {
            log.info("注册失败");
        }
        return regResult;
    }

    /**
     * @Description:redis测试案例
     * @Author: lgzhang
     * @Date:   2021/5/26 19:18
     * @Param:  []
     * @Return: void
     */
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public String tmp(){
        //redisTemplate.opsForValue().set("1","redis-Template");//spring模板
        //String str= (String) redisTemplate.opsForValue().get("1");

        jedis.set("2","jedis");
        jedis.set("2","jediss");
        jedis.set("3","jediss");
        String str2=jedis.get("3");

        return str2;
    }
}
