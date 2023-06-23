package cn.com.architecture.learn.filter;

import cn.com.architecture.learn.constant.AuthConstant;
import cn.com.architecture.learn.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@WebFilter(filterName = "JwtAuthFilter", urlPatterns = "/*")
public class JwtAuthFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = new HashSet<>();

    @Value("${auth.exclude}")
    public void setAllowedPaths(String allowedPaths) {
        JwtAuthFilter.ALLOWED_PATHS.addAll(Arrays.asList(allowedPaths.split(",")));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if(ALLOWED_PATHS.contains(path)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwtStr = request.getHeader("Authorization");
        try {
            String userName = JwtUtil.verifyAndGetClaim(jwtStr, AuthConstant.AUTH_USERNAME);
            System.out.println("登录验证成功：" + userName);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            request.setAttribute(AuthConstant.AUTH_EXCEPTION, ex);
            request.getRequestDispatcher("/auth/error").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
