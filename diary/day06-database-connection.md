# 第六天

+ 添加数据库连接配置
+ 添加持久层框架配置

## 操作步骤

+ application.properties文件中增加数据库连接配置

  配置前缀：spring.datasource

+ pom.xml文件中增加数据库连接驱动配置

  ```xml
  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
  </dependency>
  ```

+ pom.xml文件中增加持久层框架配置（Mybatis）

  ```xml
  <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>${mybatis.version}</version>
  </dependency>
  ```

+ 创建数据库测试用表

  ```sql
  create table user
  (
      user_name varchar(10) null,
      user_age  int         null
  );
  ```

+ 增加UserMapper接口、UserDTO实体类

  其中，UserMapper文件中，使用@Select注解完成查询SQL编写

+ 增加UserController文件，注入UserMapper文件，完成用户信息查询