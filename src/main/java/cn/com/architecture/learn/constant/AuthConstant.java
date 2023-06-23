package cn.com.architecture.learn.constant;

public class AuthConstant {
    public static final String AUTH_EXCEPTION = "authException";

    public static final String AUTH_USERNAME = "uname";

    public static final String AUTH_REDIS_PREFIX = "login:";

    public static String getAuthRedis(String username){
        return AUTH_REDIS_PREFIX + username;
    }
}
