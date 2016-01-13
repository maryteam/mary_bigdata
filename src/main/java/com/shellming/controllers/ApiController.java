package com.shellming.controllers;

import com.google.gson.Gson;
import com.shellming.modules.CostCountInTime;
import com.shellming.modules.UserCostInProvince;
import com.shellming.modules.UserNumInProvince;
import com.shellming.utils.CityUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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
        Gson gson = new Gson();
        List<UserNumInProvince> result = new ArrayList<>();
        List<String> strs = getResource("UserNumInProvince-" + cName);
        for(String str : strs){
            UserNumInProvince u = gson.fromJson(str, UserNumInProvince.class);
            result.add(u);
        }
        String resultStr = gson.toJson(result);
        sendResponseAsJson(response, resultStr);
    }

    @RequestMapping(value = "/api/single/citycost")
    public void getCityUserCost(HttpServletRequest request, HttpServletResponse response){
        String cName = request.getParameter("cname");
        if(cName == null)
            return;
        List<UserCostInProvince> result = new ArrayList<>();
        Gson gson = new Gson();
        List<String> strs = getResource("UserCostInProvince-" + cName);
        for(String str : strs){
            UserCostInProvince u = gson.fromJson(str, UserCostInProvince.class);
            result.add(u);
        }
        String resultStr = gson.toJson(result);
        sendResponseAsJson(response, resultStr);
    }

    @RequestMapping(value = "/api/single/costcount")
    public void getCostCount(HttpServletRequest request, HttpServletResponse response){
        String cName = request.getParameter("cname");
        if(cName == null)
            return;
        List<CostCountInTime> result = new ArrayList<>();
        Gson gson = new Gson();
        List<String> strs = getResource("CostCountInTime-" + cName);
        for(String str : strs){
            CostCountInTime u = gson.fromJson(str, CostCountInTime.class);
            result.add(u);
        }
        String resultStr = gson.toJson(result);
        sendResponseAsJson(response, resultStr);
    }

    private List<String> getResource(String name){
        ClassPathResource resource = new ClassPathResource(name);
        try {
            InputStream inputStream = resource.getInputStream();
            List<String> result = IOUtils.readLines(inputStream, "utf-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String base = "F:\\Courseware\\面向对象\\用户模型\\UserNumInProvince\\";
        String company = "财付通";

        File baseDir = new File(base, company);
        File outFile = new File(baseDir, company);
        File[] files = baseDir.listFiles();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 7, 1);
        Long time = calendar.getTimeInMillis();
        Gson gson = new Gson();
        try {
            OutputStream target = new FileOutputStream(outFile);
            for(File file : files){
                InputStream stream = new FileInputStream(file);
                List<String> lines = IOUtils.readLines(stream, "utf-8");
//                Double data = 0.0;
//                for(int i = 0; i < lines.size(); i++){
//                    UserCostInProvince u = gson.fromJson(lines.get(i), UserCostInProvince.class);
//                    data += u.getCost();
//                }
//                java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
//                data =  Double.valueOf(df.format(data));
//                String x = file.getName().replace(".txt", "");
//                UserNumInProvince model = new UserNumInProvince(
//                        x,
//                        company,
//                        data,
//                        time
//                );
//                String str = gson.toJson(model) + "\n";
                String str = lines.get(0) + "\n";
                IOUtils.write(str, target, "utf-8");
                stream.close();
            }
            target.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
