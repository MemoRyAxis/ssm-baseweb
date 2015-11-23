package com.memory.user.controller;

import com.memory.base.web.BaseController;
import com.memory.user.po.User;
import com.memory.user.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author memoryaxis@gmail.com
 * @date 2015/11/22 13:50
 */
@Controller
@RequestMapping(produces = "application/json; charset=utf8")
public class LoginController extends BaseController {

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login")
    public String login(User user) {
        user.setUsername("admin1");
        user.setPassword("100credit");
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "hello admin";
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request) {
        request.getRequestDispatcher("/welcome");
    }

    @ResponseBody
    @RequestMapping(value = "/welcome")
    public String welcome() {
        return "welcome page";
    }

    @ResponseBody
    @RequestMapping(value = "/404")
    public String errorPage() {
        return "error page";
    }
}
