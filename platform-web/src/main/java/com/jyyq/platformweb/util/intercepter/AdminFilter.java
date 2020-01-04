package com.jyyq.platformweb.util.intercepter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/*@Component
@WebFilter(urlPatterns = "/**", filterName = "adminFilter")*/
public class AdminFilter implements Filter {

    //排除不拦截的url
    private static final String[] excludePathPatterns = {"pc/login", "pc/redirect"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //获取请求url地址，不拦截excludePathPatterns中的url
        String url = request.getRequestURI();
        String requestUrl = url.replace("/jinle/", "");
        System.out.println("requestUrl="+requestUrl);
        if(Arrays.asList(excludePathPatterns).contains((requestUrl))){
            //放行
            filterChain.doFilter(request, response);
        }else{
            System.out.println("开始拦截了----------");
            response.sendRedirect(request.getContextPath()+"/pc/redirect");
            System.out.println("拦截结束");
        }
    }

    @Override
    public void destroy() {

    }
}
