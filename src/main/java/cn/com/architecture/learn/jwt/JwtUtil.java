package cn.com.architecture.learn.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

@Component
public class JwtUtil {

    // JWT秘钥
    private static Algorithm algorithm = Algorithm.HMAC256("default-secret");
    // JWT过期时间（单位秒）
    private static Integer expireSeconds = 60;

    @Value("${auth.jwt.secret:default-secret}")
    public void setJwtSecret(String jwtSecret) {
        JwtUtil.algorithm = Algorithm.HMAC256(jwtSecret);
    }

    @Value("${auth.jwt.expire.seconds:60}")
    public void setExpireSeconds(Integer expireSeconds) {
        JwtUtil.expireSeconds = expireSeconds;
    }

    /**
     * 签发JWT
     */
    public static String signJwt(Map<String, String> claimInfo) {
        JWTCreator.Builder builder = JWT.create();
        claimInfo.keySet().forEach(k -> builder.withClaim(k, claimInfo.get(k)));
        Calendar calendar = new GregorianCalendar();
        // 签发时间
        builder.withIssuedAt(calendar.getTime());
        calendar.add(GregorianCalendar.SECOND, expireSeconds);
        // 过期时间
        builder.withExpiresAt(calendar.getTime());
        return builder.sign(algorithm);
    }

    /**
     * 验证JWT并返回内容
     */
    public static String verifyAndGetClaim(String jwt, String claim) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        return decodedJWT.getClaim(claim).asString();
    }
}
