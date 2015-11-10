package com.shellming.utils;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

/**
 * Created by ruluo1992 on 11/9/2015.
 */
public class JsonUtil {
    private static Gson gson=new Gson();

    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }
}
