package cn.com.architecture.learn.controller;

import cn.com.architecture.learn.mapper.UserMapper;
import cn.com.architecture.learn.resp.Result;
import cn.com.architecture.learn.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/list")
    public Result<List<UserVO>> listUser(@RequestParam(required = false) String username) {
        List<UserVO> userList = userMapper.listUser(username);
        if (userList != null) {
            return Result.success(userList);
        }
        return Result.error("获取用户失败。");
    }

    @GetMapping("/pagelist")
    public Result<PageInfo<UserVO>> listUserByPage(@RequestParam(required = false) String username,
                                                   @RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize) {
        PageInfo<UserVO> userList = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
                userMapper.listUser(username));
        return Result.success(userList);
    }
}
