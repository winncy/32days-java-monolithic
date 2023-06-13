# 第四天

+ 添加参数校验逻辑（注解方式）

## 操作步骤

+ 引入依赖

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  ```

+ 创建需要参数验证的Controller，并添加类注释：@Validated

+ 添加参数注解，进行参数校验，如@NotNull

+ 若带校验参数为对象，需要添加@Valid注解

  > 详见ValidationController