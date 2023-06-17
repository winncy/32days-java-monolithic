# 第八天

+ 数据库工具 mybatis-plus 支持

## 操作步骤

+ pom.xml文件中增加mybatis-plus依赖配置

  ```xml
  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus.version}</version>
  </dependency>
  <!-- 引入lombok 简化entity代码 -->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
  </dependency>
  ```

+ 配置接口 UserMapper，继承 BaseMapper\<UserVO\>

+ 增加接口 IUserService，继承 IService\<UserVO\>
+ 增加IUserService实现类，继承 ServiceImpl\<UserMapper, UserVO\>，实现 IUserService

+ 增加MybatisPlusConfig配置类，配置 MybatisPlusInterceptor 拦截器

+ 配置UserVO类，增加 @TableName 类注解，增加 @TableId 主键字段注解

+ 在UserController中增加listUserByPageHelper、listUserByMapperPage、listUserByServicePage方法，分别实现分页查询用户信息的功能
+ 使用user-controller.http进行测试
