package com.shellming.utils;

import com.google.gson.Gson;
import com.shellming.modules.Location;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruluo1992 on 11/24/2015.
 */
public class CityUtil {

    private static Map<String, Location> city2loc;
    private static Map<String, List<String>> province2cities;

    // 返回城市Location对象，传入省名返回省会信息
    public static Location getLocation(String key) {
        return city2loc.get(key);
    }

    public static List<String> listProvinces(){
        List<String> provinces = new ArrayList<>(province2cities.keySet());
        return provinces;
    }

    public static List<String> listCities(String province) {
        return province2cities.get(province);
    }

    static {
        ClassPathResource resource = new ClassPathResource("china_city.json");
        city2loc = new HashMap<>();
        province2cities = new HashMap<>();
        try {
            InputStream inputStream = resource.getInputStream();
            Gson gson = new Gson();
            Map all = gson.fromJson(IOUtils.toString(inputStream, "utf-8"), Map.class);
            for(Object province : all.keySet()) {
                Map pCities = (Map) all.get(province);
                List<String> citiesStr = new ArrayList<>();
                province2cities.put(province.toString(), citiesStr);
                for(Object city : pCities.keySet()){
                    Map position = (Map) pCities.get(city);
                    Location location = new Location(
                            city.toString(),
                            province.toString(),
                            position.get("y").toString(),
                            position.get("x").toString());
                    if(!city2loc.containsKey(province.toString())) {
                        city2loc.put(province.toString(), location);
                    }
                    city2loc.put(city.toString(), location);
                    citiesStr.add(city.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println(getLocation("北京"));
//        System.out.println(listCities("山东"));
        System.out.println(listProvinces());
        System.out.println(listProvinces().size());
    }
}
