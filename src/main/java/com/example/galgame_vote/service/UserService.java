package com.example.galgame_vote.service;

import ch.qos.logback.core.util.TimeUtil;
import com.example.galgame_vote.pojo.util.JwtUtils;
import com.example.galgame_vote.pojo.util.ResponseMessage;
import com.example.galgame_vote.pojo.voting.TokenStorage;
import com.example.galgame_vote.repository.UserRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final TokenStorage tokenStorage;
    public UserService(UserRepository userRepository,
                       TokenStorage tokenStorage,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.tokenStorage = tokenStorage;
        this.jwtUtils = jwtUtils;
    }

    public ResponseMessage<Boolean> login(String account){
        if(userRepository.findByAccount(account) == null) {
            return ResponseMessage.error(false, "用户不存在", HttpStatus.NOT_FOUND.value());
        }
        // 检查用户是否已登录
        if(tokenStorage.containsAccount(account)) {
            return ResponseMessage.error(false, "用户已登录", HttpStatus.CONFLICT.value());
        }
        // 将用户信息存入Redis
        // 生成一个新的Token
        String token = jwtUtils.generateToken(userRepository.findByAccount(account));
        tokenStorage.storeToken(account, token);
        return ResponseMessage.success(true, "登录成功");
    }

}
