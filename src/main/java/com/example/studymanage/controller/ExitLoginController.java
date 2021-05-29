package com.example.studymanage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/exitLogin")
public class ExitLoginController {
    @Autowired
    private Jedis jedis;
    /**
     * @Description:退出登录-注销session
     * @Author: lgzhang
     * @Date:   2021/5/23 15:33
     * @Param:  [request]
     * @Return: void
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public void exitLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        log.info("Session已成功注销");
    }
}
