package com.shellming.controllers;

import com.google.gson.Gson;
import com.shellming.modules.UserNumInProvince;
import com.shellming.utils.CityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ruluo1992 on 1/11/2016.
 */
@Controller
public class ApiController extends BaseController{

    @RequestMapping(value = "/api/single/cityuser")
    public void getCityUserCount(HttpServletRequest request, HttpServletResponse response){
        String cName = request.getParameter("cname");
        if(cName == null)
            return;
        Random random = new Random();
        List<UserNumInProvince> result = new ArrayList<>();
        List<String> ps = CityUtil.listProvinces();
        for(String str : ps){
            UserNumInProvince u = new UserNumInProvince(
                    str,
                    cName,
                    Math.abs(random.nextInt() % 100000),
                    System.currentTimeMillis());
            result.add(u);
        }
        Gson gson = new Gson();
        String resultStr = gson.toJson(result);
        sendResponseAsJson(response, resultStr);
    }
}
