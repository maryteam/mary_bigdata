package com.shellming.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping(value = "/storage")
    @ResponseBody
    public String storage(HttpServletRequest request){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String key = request.getParameter("key");
        String value = request.getParameter("value");

        String homeDir = System.getProperty("user.home");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = format.format(date);
        File dataDir = new File(homeDir, time);
        dataDir.mkdir();

        File file = new File(dataDir, key);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(value);
            writer.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "test")
    @ResponseBody
    public String storageTest(){
        String homeDir = System.getProperty("user.home");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = format.format(date);
        File dataDir = new File(homeDir, time);
        dataDir.mkdirs();

        File file = new File(dataDir, "中文测试");
        try {
            FileUtils.write(file, "测试中文", "utf-8", true);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

}
