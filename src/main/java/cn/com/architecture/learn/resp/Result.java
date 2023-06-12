package cn.com.architecture.learn.resp;

public class Result<T> {
    boolean success;
    String code;
    String message;
    T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, "200", "", data);
    }

    public static <T> Result<T> error(Exception e) {
        return error(e.getMessage());
    }

    public static <T> Result<T> error(String errorMessage) {
        return error("500", errorMessage);
    }

    public static <T> Result<T> error(String errorCode, String errorMessage) {
        return new Result<>(false, errorCode, errorMessage, null);
    }

    public Result(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
