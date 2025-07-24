package com.example.galgame_vote.pojo;

import com.example.galgame_vote.pojo.util.JwtUtils;
import com.example.galgame_vote.pojo.voting.TokenStorage;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class VoteAspect {

    //鉴别token是否存在
    private final TokenStorage tokenStorage;
    private final JwtUtils jwtUtils;

    public VoteAspect(TokenStorage tokenStorage,
                       JwtUtils jwtUtils) {
        this.tokenStorage = tokenStorage;
        this.jwtUtils = jwtUtils;
    }

    @Pointcut("execution(* com.example.galgame_vote.controller.VotingController.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        if (!isAuthorized(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("未登录");
        }

        return joinPoint.proceed();
    }

    private boolean isAuthorized(String token) {
        return token != null && !token.isEmpty() && tokenStorage.containsAccount(jwtUtils.getAccountFromToken(token));
    }

}
