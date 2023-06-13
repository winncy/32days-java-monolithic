package cn.com.architecture.learn;

import cn.com.architecture.learn.req.RequestData;
import cn.com.architecture.learn.resp.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/validate")
public class ValidationController {

    @RequestMapping("/requestparam")
    Result<?> validateRequestParam(@NotNull(message = "param1不能为空") String param1) {
        return Result.success(param1);
    }

    @RequestMapping(value = "/requestbody", method = RequestMethod.POST)
    Result<?> validateRequestBody(@Valid @RequestBody RequestData requestData) {
        return Result.success(requestData);
    }

}
