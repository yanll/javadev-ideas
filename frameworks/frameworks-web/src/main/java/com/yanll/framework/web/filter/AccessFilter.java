package com.yanll.framework.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AccessFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

        } catch (Exception e) {
            logger.error("AccessFilter:ERROR:", e);
        } finally {
            response.setHeader("Access-Control-Allow-Origin", "*");// 跨域处理
            filterChain.doFilter(request, response);
        }
    }


}
