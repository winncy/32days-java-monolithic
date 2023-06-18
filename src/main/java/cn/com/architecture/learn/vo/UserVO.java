package cn.com.architecture.learn.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserVO {
    @TableId(type = IdType.ASSIGN_ID)
    String userName;
    Integer userAge;
    String password;
}
