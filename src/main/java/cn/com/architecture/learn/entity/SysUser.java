package cn.com.architecture.learn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;
    @TableId
    private Long id;
    private String userName;
    private String nickName;
    private String password;
}
