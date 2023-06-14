package cn.com.architecture.learn.controller;

import cn.com.architecture.learn.resp.ResponseData;
import cn.com.architecture.learn.resp.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @RequestMapping("/")
    String hello() {
        return "Hello SpringBootApplication";
    }

    @RequestMapping("/success")
    Result<?> successResult() {
        return Result.success();
    }

    @RequestMapping("/successdata")
    Result<?> successResultData() {
        return Result.success(new ResponseData("test1", "test2"));
    }

    @RequestMapping("/errormessage")
    Result<?> errorResult() {
        return Result.error("操作失败。");
    }

    @RequestMapping("/errorexception")
    Result<?> errorResultException() {
        return Result.error(new RuntimeException("运行时异常。"));
    }

    @RequestMapping("/errorcodemessage")
    Result<?> errorResultCodeMessage() {
        return Result.error("502", "自定义异常。");
    }
}
