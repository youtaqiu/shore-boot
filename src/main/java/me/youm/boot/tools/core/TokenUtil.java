package me.youm.boot.tools.core;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.youm.boot.constant.TokenConstant;
import me.youm.boot.web.exception.auth.token.TokenValidationException;

import java.util.Date;

/**
 * Token 工具类
 *
 * @author youta
 */
public class TokenUtil {

    /**
     * 创建 Token
     *
     * @param userId   用户编号
     * @param username 用户账号
     */
    public static String create(String userId, String username) {
        return Jwts.builder()
                .setId(userId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer(TokenConstant.ISSUER)
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();
    }

    /**
     * 解析 Token
     *
     * @param token Token
     */
    public static void parse(String token) {
        try {
            Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new TokenValidationException("token validation failure");
        }
    }
}
