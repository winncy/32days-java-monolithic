package cn.com.architecture.learn.controller;

import cn.com.architecture.learn.constant.AuthConstant;
import cn.com.architecture.learn.jwt.JwtUtil;
import cn.com.architecture.learn.resp.Result;
import cn.com.architecture.learn.service.IUserService;
import cn.com.architecture.learn.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        UserVO user = userService.getOne(new QueryWrapper<UserVO>().eq("user_name", username));
        if (user != null && user.getPassword().equals(password)) {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put(AuthConstant.AUTH_USERNAME, username);
            return Result.success(JwtUtil.signJwt(userInfo));
        }
        return Result.error("验证失败。");
    }

    @RequestMapping("/error")
    public Result<?> error(HttpServletRequest request) {
        return Result.error((Exception) request.getAttribute(AuthConstant.AUTH_EXCEPTION));
    }
}
