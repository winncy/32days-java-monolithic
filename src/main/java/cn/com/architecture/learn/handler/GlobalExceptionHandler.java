package cn.com.architecture.learn.handler;

import cn.com.architecture.learn.resp.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 加了@RequestParam注解，但是接口调用时没有传指定的参数
     *
     * @param ex MissingServletRequestParameterException
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return Result.error(String.format("缺少必要参数[%s]", ex.getParameterName()));
    }

    /**
     * 传了值，但是不符合要求，如：
     * NotNull(message = “最大值不能为空”)
     * Min(value = 10,message = “参数必须大于10”)
     * 当校验参数直接写在方法上，而不是写在类中，报错会被该捕获器捕获。
     *
     * @param ex ConstraintViolationException
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        String exMessage = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.error("".equals(exMessage) ? "参数校验失败。" : exMessage);
    }

    /**
     * MethodArgumentNotValidException 当校验的参数放在对象中，接口的请求方式是post请求，用@Valid @RequestBody方式接受参数时，如果报错，会被该捕获器捕获。
     * BindException 当校验参数写在类中，接口请求方式是get请求时，报错会被该捕获器捕获。
     *
     * @param ex MethodArgumentNotValidException BindException
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<?> handleMethodArgumentNotValidException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String exMessage = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.error("".equals(exMessage) ? "参数校验失败。" : exMessage);
    }

    /**
     * 其他所有异常捕获器
     *
     * @param ex Exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handleException(Exception ex) {
        ex.printStackTrace();
        return Result.error("接口处理失败：" + ex.getMessage());
    }
}
