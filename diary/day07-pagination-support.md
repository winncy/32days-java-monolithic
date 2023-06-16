# 第七天

+ 增加分页查询支持

## 操作步骤

+ pom文件中增加对mybatis分页插件pagehelper的引用

  ```xml
  <dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>${pagehelper.version}</version>
  </dependency>
  ```

+ UserController中增加分页查询方法listUserByPage，并增加分页参数pageNum和pageSize

+ 使用PageHelper.*startPage*(pageNum, pageSize)方法开启分页

  ```java
  
  ```

  > 更多使用方法：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md

+ 使用user-controller.http进行测试，返回分页结果

  ```json
  {
    "success": true,
    "code": "200",
    "message": "",
    "data": {
      "total": 12,
      "list": [
        {
          "userName": "张三1",
          "userage": 18
        },
        {
          "userName": "张三2",
          "userage": 19
        },
        {
          "userName": "张三3",
          "userage": 20
        }
      ],
      "pageNum": 1,
      "pageSize": 3,
      "size": 3,
      "startRow": 1,
      "endRow": 3,
      "pages": 4,
      "prePage": 0,
      "nextPage": 2,
      "isFirstPage": true,
      "isLastPage": false,
      "hasPreviousPage": false,
      "hasNextPage": true,
      "navigatePages": 8,
      "navigatepageNums": [
        1,
        2,
        3,
        4
      ],
      "navigateFirstPage": 1,
      "navigateLastPage": 4
    }
  }
  ```

  