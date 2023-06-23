package cn.com.architecture.learn.service.impl;

import cn.com.architecture.learn.constant.AuthConstant;
import cn.com.architecture.learn.entity.SysUser;
import cn.com.architecture.learn.jwt.JwtUtil;
import cn.com.architecture.learn.redis.RedisCache;
import cn.com.architecture.learn.resp.Result;
import cn.com.architecture.learn.security.LoginUser;
import cn.com.architecture.learn.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements ILoginService {


    private AuthenticationManager authenticationManager;

    private RedisCache redisCache;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    @Override
    public Result<?> login(SysUser user) {
        // 初始化验证对象
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        // 进行用户名密码验证
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            return Result.error("用户名或密码错误");
        }
        // 获取用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 生成JWT
        String userName = loginUser.getUsername();
        String jwt = JwtUtil.signJwtWithUsername(userName);
        // 存储用户信息
        redisCache.setCacheObject(AuthConstant.getAuthRedis(userName), loginUser);
        // 组织返回值
        Map<String, String> rsMap = new HashMap<>();
        rsMap.put("jwt", jwt);
        return Result.success(rsMap);
    }
}
