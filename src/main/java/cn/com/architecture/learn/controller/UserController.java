package cn.com.architecture.learn.controller;

import cn.com.architecture.learn.mapper.UserMapper;
import cn.com.architecture.learn.resp.Result;
import cn.com.architecture.learn.service.IUserService;
import cn.com.architecture.learn.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    IUserService userService;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Result<List<UserVO>> listUser(@RequestParam(required = false) String username) {
        List<UserVO> userList = userService.list(new QueryWrapper<UserVO>().like("user_name", username + "%"));
        if (userList != null) {
            return Result.success(userList);
        }
        return Result.error("获取用户失败。");
    }

    @GetMapping("/list/pagehelper")
    public Result<PageInfo<UserVO>> listUserByPageHelper(@RequestParam(required = false) String username,
                                                   @RequestParam Integer pageNum,
                                                   @RequestParam Integer pageSize) {
        PageInfo<UserVO> userList = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() ->
                userMapper.listUser(username));
        return Result.success(userList);
    }

    @GetMapping("/list/page/mapper")
    public Result<IPage<UserVO>> listUserByMapperPage(@RequestParam(required = false) String username,
                                                @RequestParam Integer pageNum,
                                                @RequestParam Integer pageSize) {
        IPage<UserVO> page = new Page<>(pageNum, pageSize);
        IPage<UserVO> rsList = userMapper.selectPage(page, new QueryWrapper<UserVO>().likeRight(StringUtils.checkValNotNull(username), "user_name", username));
        return Result.success(rsList);
    }

    @GetMapping("/list/page/service")
    public Result<IPage<UserVO>> listUserByServicePage(@RequestParam(required = false) String username,
                                                 @RequestParam Integer pageNum,
                                                 @RequestParam Integer pageSize) {
        IPage<UserVO> page = new Page<>(pageNum, pageSize);
        IPage<UserVO> rsList = userService.page(page, new QueryWrapper<UserVO>().likeRight(StringUtils.checkValNotNull(username), "user_name", username));
        return Result.success(rsList);
    }
}
