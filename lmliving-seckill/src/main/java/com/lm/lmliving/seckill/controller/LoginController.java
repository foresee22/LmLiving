package com.lm.lmliving.seckill.controller;

import com.lm.lmliving.seckill.vo.LoginVo;
import com.lm.lmliving.seckill.vo.RespBean;
import com.lm.lmliving.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author lm
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    //装配UserService
    @Resource
    private UserService userService;

    //编写方法,可以进入到登录页面
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login"; //到templates/login.html
    }

    //方法: 处理用户登录请求
    //回顾springboot的相关知识
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        //log.info("{}", loginVo);//观察数据
        return userService.doLogin(loginVo, request, response);
    }

}
