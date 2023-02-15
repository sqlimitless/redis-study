package com.sqlimitless.redisstudy.cachetest.service;

import com.sqlimitless.redisstudy.cachetest.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;
    private final StringRedisTemplate stringRedisTemplate;

    public UserProfile getUserProfile(String userId) {
        String userName;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String cacheName = ops.get("nameKey_" + userId);
        if (cacheName != null) {
            userName = cacheName;
        } else {
            userName = externalApiService.getUserName(userId);
            ops.set("nameKey_" + userId,userName,10, TimeUnit.SECONDS);
        }
        int userAge = externalApiService.getUserAge(userId);
        return new UserProfile(userName, userAge);
    }
}
