# 第十天

+ 实现接口身份认证（SpringSecurity+JWT实现）

## 操作步骤

+ pom.xml文件中增加SpringSecurity、redis、fastjson的引用

  ```xml
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>${fastjson.version}</version>
  </dependency>
  ```

  其中，redis负责存储登录用户信息，fastjson负责对登录用户信息序列化/反序列化

+ 增加SpringSecurity相关配置及实现
  + 增加UserDetails接口实现类：LoginUser
  + 增加UserDetailsService接口实现类：UserDetailsServiceImpl
  + 增加SpringSecurity主要配置类：SecurityConfig（配置认证方式、匿名访问地址、过滤器、密码策略等）
  + 增加登录认证过滤器：JwtAuthenticationTokenFilter，并在SecurityConfig进行配置

+ 增加Redis配置及实现
  + 增加Redis配置类 RedisConfig，负责配置Redis序列化与反序列化逻辑
  + 增加Redis工具类 RedisCache
  + 增加RedisSerializer接口实现类：FastJsonRedisSerializer

+ 增加用户信息数据库操作实体及服务
  + 增加用户信息实体类：SysUser
  + 增加用户信息实体数据库操作Mapper：SysUserMapper

+ 增加登录相关逻辑

  + 在UserController中增加/login登录接口
  + 增加登录服务接口及实现类：ILoginService、LoginServiceImpl

+ 增加数据库中用户信息配置

  + 创建用户信息表

    ```sql
    create table sys_user
    (
        id        int auto_increment comment '主键' primary key,
        user_name varchar(64) default 'NULL' not null comment '用户名',
        nick_name varchar(64) default 'NULL' not null comment '昵称',
        password  varchar(64) default 'NULL' not null comment '密码'
    )
        comment '用户表';
    ```

  + 插入测试用户数据

    ```sql
    INSERT INTO sys_user (user_name, nick_name, password) VALUES ('test', '测试账号', '123456');
    ```

    