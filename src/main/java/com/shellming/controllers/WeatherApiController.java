package com.shellming.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ruluo1992 on 1/12/2016.
 */
@Controller
@RequestMapping(value = "/api/weather/v2")
public class WeatherApiController extends BaseController{

    @RequestMapping(value = "observations/current")
    public void getCurrentObservation(HttpServletRequest request, HttpServletResponse response){
        String json = getResource("current.json");
        sendResponseAsJson(response, json);
    }

    @RequestMapping(value = "forecast/hourly/24hour")
    public void get24HourForcast(HttpServletResponse response){
        String json = getResource("24hour.json");
        sendResponseAsJson(response, json);
    }

    @RequestMapping(value = "forecast/daily/10day")
    public void get10dayForcast(HttpServletResponse response){
        String json = getResource("10day.json");
        sendResponseAsJson(response, json);
    }

    private String getResource(String name){
        ClassPathResource resource = new ClassPathResource(name);
        try {
            InputStream inputStream = resource.getInputStream();
            String json = IOUtils.toString(inputStream, "utf-8");
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
