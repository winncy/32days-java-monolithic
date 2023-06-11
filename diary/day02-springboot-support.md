# 第二天

+ 添加Springboot架构支持
+ 启动web应用，完成HelloWorld程序

## 操作步骤

+ pom.xml文件中添加spring-boot-starter-parent的项目继承配置
+ pom.xml文件中添加spring-boot-starter-web的maven引用配置

+ 增加MyApplication.java启动类，负责web项目启动
+ 添加MyController.java控制类，负责路由信息配置（使用Spring MVC的注解：@RestController、@RequestMapping）
+ 添加配置文件application.properties，负责Springboot项目配置（如启动端口参数 server.port=8888）