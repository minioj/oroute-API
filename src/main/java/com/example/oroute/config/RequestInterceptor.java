package com.example.oroute.config;

import com.example.oroute.service.AuthService;
import com.example.oroute.utils.HttpHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @Author mini_oj
* @category 对请求头进行校验
* @Description
* @Date 2020-04-04 16:18
* @Param
**/
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    long start = System.currentTimeMillis();

    //preHandle是在请求执行前执行的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //返回true,postHandler和afterCompletion方法才能执行
        // 否则false为拒绝执行，起到拦截器控制作用
        response.setCharacterEncoding("utf-8");
        String authUserId = request.getHeader("authUserId");
        String authPassword = request.getHeader("authPassword");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("AskDate   : ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            sb.append("Params    : ").append(HttpHelper.getBodyString(request)).append("\n");
            sb.append("URI       : ").append(request.getRequestURI()).append("\n");
            logger.info(sb.toString());
        }

        // 请求头校验
        if (authUserId == null || authPassword == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", "401");
            map.put("msg", "当前请求未或授权，请检查！");
            map.put("info", "Unauthorized");
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(map));
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setStatus(401);
            return false;
        } else {
            boolean isAuthAccess = authService.userAuth(authUserId, authPassword);
            if (isAuthAccess) {
                // 认证成功
                return true;
            } else {
                // 认证失败
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", "401");
                map.put("msg", "用户名或密码错误");
                map.put("info", "Unauthorized");
                Gson gson = new Gson();
                response.getWriter().write(gson.toJson(map));
                response.setHeader("Content-Type", "Application/json;charset=utf-8");
                response.setStatus(401);
                return false;
            }
        }
    }

    //postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("Interception cost="+(System.currentTimeMillis()-start));
    }

    //afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true，
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //该方法通常用于清理资源等工作
    }
}