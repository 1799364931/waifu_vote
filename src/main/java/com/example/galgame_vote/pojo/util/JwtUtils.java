package com.example.galgame_vote.pojo.util;

import com.example.galgame_vote.pojo.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    private final String secret = Base64.getEncoder().encodeToString(new byte[32]);
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    // 生成 Token
    public String generateToken(User user) {
        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .claim("account", user.getAccount())
                .claim("id", user.getId().toString())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24L))
                .id(UUID.randomUUID().toString())
                .signWith(secretKey)
                .compact();
    }

    // 验证 Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload();
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token 已过期");
            return false;
        } catch (MalformedJwtException e) {
            System.out.println("Token 格式错误");
            return false;
        } catch (SecurityException e) {
            System.out.println("Token 签名无效");
            return false;
        } catch (Exception e) {
            System.out.println("Token 验证失败");
            return false;
        }
    }

    public String getAccountFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("account", String.class);
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public UUID getIdFromToken(String token) {
        return UUID.fromString(Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", String.class));
    }



}