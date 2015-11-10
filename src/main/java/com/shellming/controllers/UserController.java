package com.shellming.controllers;

import com.shellming.utils.CookieUtil;
import com.shellming.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ruluo1992 on 11/9/2015.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameterMap());
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        System.out.println(userName);
        System.out.println(passWord);
        Map<String, Object> result = new HashMap<>();
        if("admin".equals(userName) && "admin".equals(passWord)) {
            result.put("status", 0);
            CookieUtil.setUserNameToCookie(response, userName);
        }
        else {
            result.put("status", 1);
            result.put("message", "用户名或密码错误！");
        }
        String responseStr = JsonUtil.toJson(result);
        sendResponseAsJson(response, responseStr);
    }
}
