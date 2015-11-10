package com.shellming.filters;

import com.shellming.utils.CookieUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ruluo1992 on 11/10/2015.
 */
public class PermissionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if(url.contains("pages/login.html")){
            filterChain.doFilter(request, response);
            return;
        }
        String name = CookieUtil.getUserNameFromCookie(request);
        if(StringUtils.isEmpty(name)){
            response.sendRedirect("/pages/login.html");
            return;
        }
        else{
            filterChain.doFilter(request, response);
        }
    }
}
