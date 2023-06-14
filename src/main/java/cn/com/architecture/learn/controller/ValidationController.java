package cn.com.architecture.learn.controller;

import cn.com.architecture.learn.req.RequestData;
import cn.com.architecture.learn.resp.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/validate")
public class ValidationController {

    @RequestMapping("/requestparam")
    Result<?> validateRequestParam(@RequestParam String param1, @NotNull(message = "param2不能为空") String param2) {
        return Result.success(String.format("%s-%s", param1, param2));
    }

    @RequestMapping("/requestparamobj")
    Result<?> validateRequestParamObj(@Valid @RequestParam RequestData param) {
        return Result.success(param);
    }

    @RequestMapping(value = "/requestbody", method = RequestMethod.POST)
    Result<?> validateRequestBody(@Valid @RequestBody RequestData requestData) {
        return Result.success(requestData);
    }

}
