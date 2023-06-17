package cn.com.architecture.learn.service.impl;

import cn.com.architecture.learn.mapper.UserMapper;
import cn.com.architecture.learn.service.IUserService;
import cn.com.architecture.learn.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserVO> implements IUserService {
}
