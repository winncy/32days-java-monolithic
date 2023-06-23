package cn.com.architecture.learn.security;

import cn.com.architecture.learn.entity.SysUser;
import cn.com.architecture.learn.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    SysUserMapper sysUserMapper;

    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("请输入用户名");
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username).last("limit 1");
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名不存在");
        }
        return new LoginUser(user);
    }
}
