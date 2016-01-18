package com.shellming.controllers;

import com.google.gson.Gson;
import com.shellming.modules.CostCountInTime;
import com.shellming.modules.EnterpriseRank;
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

    @RequestMapping(value = "/api/multi/association")
    public void getAssociation(HttpServletResponse response){
        String[] data = new String[]{
                " 1. 财付通 703897 ==> 腾讯 703750    conf:(1)",
                " 2. 神州租车 财付通 5104 ==> 腾讯 5090    conf:(1)",
                " 3. 京东 财付通 7160 ==> 腾讯 7137    conf:(1)",
                " 4. 腾讯 752237 ==> 财付通 703750    conf:(0.94)",
                " 5. 滴滴 19133 ==> 支付宝 17312    conf:(0.9)",
                " 6. 支付宝 21083 ==> 滴滴 17312    conf:(0.82)",
                " 7. 京东 79410 ==> 腾讯 45029    conf:(0.57)",
                " 8. 神州租车 48004 ==> 京东 25876    conf:(0.54)",
                " 9. 腾讯 神州租车 11266 ==> 财付通 5090    conf:(0.45)",
                "10. 京东 79410 ==> 神州租车 25876    conf:(0.33)",
                "11. 神州租车 48004 ==> 腾讯 11266    conf:(0.23)",
                "12. 京东 腾讯 45029 ==> 财付通 7137    conf:(0.16)",
                "13. 神州租车 48004 ==> 财付通 5104    conf:(0.11)",
                "14. 神州租车 48004 ==> 腾讯 财付通 5090    conf:(0.11)",
                "15. 京东 79410 ==> 财付通 7160    conf:(0.09)",
                "16. 京东 79410 ==> 腾讯 财付通 7137    conf:(0.09)",
                "17. 腾讯 752237 ==> 京东 45029    conf:(0.06)",
                "18. 腾讯 752237 ==> 神州租车 11266    conf:(0.01)",
                "19. 财付通 703897 ==> 京东 7160    conf:(0.01)",
                "20. 腾讯 财付通 703750 ==> 京东 7137    conf:(0.01)",
                "21. 财付通 703897 ==> 京东 腾讯 7137    conf:(0.01)",
                "22. 腾讯 752237 ==> 京东 财付通 7137    conf:(0.01)",
                "23. 财付通 703897 ==> 神州租车 5104    conf:(0.01)",
                "24. 腾讯 财付通 703750 ==> 神州租车 5090    conf:(0.01)",
                "25. 财付通 703897 ==> 腾讯 神州租车 5090    conf:(0.01)",
                "26. 腾讯 752237 ==> 神州租车 财付通 5090    conf:(0.01)"
        };
        Gson gson = new Gson();
        sendResponseAsJson(response, gson.toJson(Arrays.asList(data)));
    }

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

    @RequestMapping(value = "/api/single/enterpriserank")
    public  void getEnterpriseRank(HttpServletRequest request, HttpServletResponse response){
        //String cName = request.getParameter("cname");
//        if(cName == null)
//            return;
        List<EnterpriseRank> result = new ArrayList<>();
        Gson gson = new Gson();
        List<String> strs = getResource("EnterPriseRank");
        System.out.println("!!!");
        for(String str : strs){
            EnterpriseRank u = gson.fromJson(str, EnterpriseRank.class);
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
