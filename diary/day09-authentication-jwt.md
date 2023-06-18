# 第九天

+ 增加接口校验逻辑

## 操作步骤

+ pom.xml文件增加jwt支持

  ``` xml
  <dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>${auth0-jwt.version}</version>
  </dependency>
  ```

+ 增加Jwt工具类JwtUtil.java

  提供签发JWT、验证JWT并获取信息方法

+ 增加JWT验证过滤器配置JwtAuthFilter.java

  实现Filter接口，并配置@WebFilter注解

+ 启动类增加@ServletComponentScan注解配置，使过滤器生效

+ 增加AuthController.java 

  实现登录接口：登录成功返回JWT，后续接口请求携带JWT进行认证

+ 在user表中增加password字段，用于登录接口校验

  ```sql
  alter table user
      add password varchar(100) default '123456' not null comment '密码';
  ```

+ 增加auth-controller.http测试登录接口
+ 修改user-controller.http中的接口参数，增加Authorization参数，传入JWT进行认证