package com.example.oroute.execption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 功能说明: 全局异常处理
 * 作者 yangxiaojun2017
 * 日期 2019/6/6 11:59
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 功能说明: 自定义响应码
     * 参数: e
     * 参数: response
     * 作者 yangxiaojun2017
     * 日期 2019/6/6 12:01
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public MyExceptionResponse myExceptionHandler(MyException e, HttpServletResponse response) {
        String msg = e.getMessage();
        try {
            msg = URLEncoder.encode(e.getMessage(), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        MyExceptionResponse resp = new MyExceptionResponse(0,"");
        resp.setMsg(e.getMessage());
        resp.setCode(e.getCode());
        response.setStatus(e.getCode()); // 响应码
        response.setHeader("msg", msg);
        return resp;
    }

    /**
     * 功能说明: 全局异常处理
     * 参数: e
     * 参数: response
     * 作者 yangxiaojun2017
     * 日期 2019/6/6 12:02
     */
    @ExceptionHandler
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public MyExceptionResponse exceptionHandler(Exception e, HttpServletResponse response) {
        String msg = e.getMessage();
        try {
            msg = URLEncoder.encode(e.getMessage(), "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        MyExceptionResponse resp = new MyExceptionResponse(0,"");
        resp.setMsg(e.getMessage());
        resp.setCode(500);
        response.setStatus(500); // 响应码
        response.setHeader("msg", msg);
        return resp;
    }
}
