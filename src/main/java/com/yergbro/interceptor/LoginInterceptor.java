//package com.yergbro.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 获取session
//        HttpSession session = request.getSession(true);
//        // 判断用户ID是否存在，不存在就跳转到登录界面
//        String name = (String)session.getAttribute("name");
//        int remotePort = request.getRemotePort();
//        String servletPath = request.getServletPath();
//        StringBuffer requestURL = request.getRequestURL();
//        System.out.println(name);
//        System.out.println(remotePort);
//        System.out.println(servletPath);
//        System.out.println(requestURL);
//        if(remotePort==8080) return true;
//        if (name== null) {
//            return false;
//        } else {
//            return true;
//        }
//
//    }
//}
