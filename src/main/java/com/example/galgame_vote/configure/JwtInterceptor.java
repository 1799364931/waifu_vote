package com.example.galgame_vote.configure;

import com.example.galgame_vote.pojo.util.JwtUtils;
import com.example.galgame_vote.pojo.voting.TokenStorage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    private final TokenStorage tokenStorage;
    private final JwtUtils jwtUtils;

    @Autowired
    public JwtInterceptor(TokenStorage tokenStorage,JwtUtils jwtUtils){
        this.tokenStorage = tokenStorage;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws IOException {
        String path = request.getRequestURL().toString();
        if(path.startsWith("/login") || path.startsWith("/home")){
            return true;
        }
        String token = request.getHeader("Authorization").replace("Bearer", "");
        String account = jwtUtils.getAccountFromToken(token);
        if (!token.equals(tokenStorage.getToken(account)) && jwtUtils.validateToken(token)) {
            response.sendRedirect("/login"); // Token 无效，跳转到登录页
            return false;
        }
        return true;
    }
}
