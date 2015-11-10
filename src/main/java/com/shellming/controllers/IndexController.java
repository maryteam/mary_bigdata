package com.shellming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruluo1992 on 10/3/2015.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        return "redirect:/pages/login.html";
    }

}
