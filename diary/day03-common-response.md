# 第三天

+ 添加接口统一返回封装类Result

## 操作步骤

+ 添加Result封装类success方法集
  + Result\<T> success(T data)
  + Result\<T> success()
+ 添加Result封装类error方法集
  + Result\<T> error(String errorCode, String errorMessage)
  + Result\<T> error(String errorMessage)
  + Result\<T> error(Exception e)

+ 在MyController类中使用Result<?>进行返回

