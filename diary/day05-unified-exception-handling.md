# 第五天

+ 增加统一异常处理逻辑

## 操作步骤

+ 增加统一异常处理配置类 GlobalExceptionHandler
+ 使用@RestControllerAdvice注解对统一异常处理类进行标注
+ 增加@ExceptionHandler注解方法，分别添加MissingServletRequestParameterException、ConstraintViolationException、MethodArgumentNotValidException、Exception异常处理逻辑
+ 使用src/test/resources/validate-controller.http进行测试

