# 第一天

+ 添加Maven架构支持
+ 编写HelloWorld程序

## 操作步骤

+ 项目右键 -> 添加框架支持 -> 选择Maven
+ 新建HelloWorld.java，编写HelloWorld程序

## 相关概念

+ Maven是什么？Maven能用来做什么？

  Maven是Apache旗下的一个开源项目，是一个项目管理和构建工具，它基于项目对象模型(Project Object Model , 简 称: POM)的概念，通过一小段描述信息来管理项目的构建、报告和文档。
  
  掌握Maven可以进行一些简单的依赖管理、 统一项目结构、 项目构建。
  + 依赖管理
  
    方便快捷的管理项目依赖的资源(jar包)，避免版本冲突问题。
  
  + 统一项目结构
  
    提供标准、统一的项目结构
  
    ```
    目录说明： 
    src/main/java: java源代码目录 
    src/main/resources: 配置文件信息 
    src/test/java: 测试代码 
    src/test/resources: 测试配置文件信息
    ```
  
  + 项目构建
  
    maven提供了标准的、跨平台的自动化项目构建方式。
  
    构建过程：清理、编译、测试、打包、发布
