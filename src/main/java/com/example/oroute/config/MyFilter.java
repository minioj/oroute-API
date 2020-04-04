package com.example.oroute.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @Author mini_oj
* @category 
* @Description 
* @Date 2020-04-04 19:48
* @Param 
**/
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void destroy() {

    }

    /**
    * @Author mini_oj
    * @category
    * @Description 防止Stream closed
    * @Date 2020-04-04 21:57
    * @Param
    **/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        BodyReaderHttpServletRequestWrapper requestWrapper = null;
        requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        requestWrapper.getInputStream();
        //获取请求中的流如何，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
        // 在chain.doFiler方法中传递新的request对象
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
