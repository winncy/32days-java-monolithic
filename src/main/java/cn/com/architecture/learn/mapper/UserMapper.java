package cn.com.architecture.learn.mapper;

import cn.com.architecture.learn.vo.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserVO> {
    @Select("select user_name username, user_age userage from user where user_name like '${username}%'")
    List<UserVO> listUser(String username);
}
