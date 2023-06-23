package cn.com.architecture.learn.service;

import cn.com.architecture.learn.entity.SysUser;
import cn.com.architecture.learn.resp.Result;

public interface ILoginService {
    Result<?> login(SysUser user);
}
