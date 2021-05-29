package com.example.studymanage.controller;

import com.example.studymanage.domain.User;
import com.example.studymanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Resource
    private Jedis jedis;

    /**
     * @Description:用户登录
     * @Author: lgzhang
     * @Date:   2021/5/23 15:33
     * @Param:  [user, request]
     * @Return: java.lang.String
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String loginCheck(@RequestBody User user, HttpServletRequest request){
        log.info("请求的user"+user);
        HttpSession session = request.getSession();
        String id = user.getId();
        String password = user.getPassword();
        if(id==null||password==null){
            log.info("账号或密码为空");
            return "false";
        }
        if(userService.checkPwd(id,password)){

            // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
            //jedis.set(id,session.getId(),"NX","EX",10);//将成功登录的用户id和session存入redis，以此进行验证

            jedis.set(id,session.getId());//登录成功设置，注销时删除，下次登陆时会覆盖-暂不设置失效时间

            session.setAttribute("id",id);
            String token = "KSH2541SIA25SA";//
            return token;
        }else {
            return "false";
        }
    }
}
