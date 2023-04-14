package com.criown.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClientInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        System.out.println("==========Interceptor===========");
        System.out.println( "GotoURL::"+request.getRequestURI());
        if(session.getAttribute("clientLogInfo")!=null){
            System.out.println("session符合");
            return true;
        }
        if(request.getRequestURI().contains("log")){
            System.out.println("检测到跳转登录");
            return true;
        }

        System.err.println("触发客户拦截 自动跳转");
        //拦截
        request.getRequestDispatcher("/WEB-INF/jsp/login/logPage.jsp").forward(request,response);
        return false;
        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       // System.out.println("==========Interceptor===========");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
