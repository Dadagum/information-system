package com.dadagum.interceptor;

import com.dadagum.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPriorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("in CheckPriorityInterceptor : pre handle");
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null || user.getPriority() == null || user.getPriority().equals("") || !user.getPriority().equals("admin")){
            //httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpServletResponse.sendRedirect("/error.html");
            System.out.println("deny access : admin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
