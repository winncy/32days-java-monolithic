package cn.com.architecture.learn.filter;

import cn.com.architecture.learn.constant.AuthConstant;
import cn.com.architecture.learn.jwt.JwtUtil;
import cn.com.architecture.learn.redis.RedisCache;
import cn.com.architecture.learn.security.LoginUser;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    RedisCache redisCache;

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LoginUser loginUser;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isBlank(jwt)){
            // 此处直接放行，交给后续过滤器处理，如 UsernamePasswordAuthenticationFilter
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String username = JwtUtil.verifyAndGetClaim(jwt, AuthConstant.AUTH_USERNAME);
            loginUser = redisCache.getCacheObject(AuthConstant.getAuthRedis(username));
            if (Objects.isNull(loginUser)) {
                throw new RuntimeException("获取用户信息失败。");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute(AuthConstant.AUTH_EXCEPTION, ex);
            request.getRequestDispatcher("/auth/error").forward(request, response);
            return;
        }

        // 组织SpringSecurity上下文
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}
