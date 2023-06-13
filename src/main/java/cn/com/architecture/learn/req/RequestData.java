package cn.com.architecture.learn.req;

import javax.validation.constraints.NotNull;

public class RequestData {
    @NotNull(message = "param1参数不能为空。")
    String param1;
    @NotNull(message = "param2参数不能为空。")
    String param2;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
