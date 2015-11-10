package com.shellming.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ruluo1992 on 11/10/2015.
 */
public class CookieUtil {

    private static String NAME_COOKIE = "LOGIN";

    public static String getUserNameFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            return null;
        boolean isLogin = false;
        for(Cookie cookie : cookies){
            String name = cookie.getName();
            if(name.equals(NAME_COOKIE)){
                String value = cookie.getValue();
                if(!StringUtils.isEmpty(value)){
                    return value;
                }
            }
        }
        return null;
    }

    public static void setUserNameToCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(NAME_COOKIE, name);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
