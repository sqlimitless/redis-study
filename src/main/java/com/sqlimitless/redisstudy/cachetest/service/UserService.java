package com.sqlimitless.redisstudy.cachetest.service;

import com.sqlimitless.redisstudy.cachetest.dto.UserEntity;
import com.sqlimitless.redisstudy.cachetest.dto.UserProfile;
import com.sqlimitless.redisstudy.cachetest.infrastructer.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserProfileRepository userProfileRepository;


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
    @Cacheable( cacheNames = "cacheTest", key = "#id")
    @Transactional
    public UserEntity cacheFindById(long id) {
        System.out.println("UserService.cacheFindById #############################");
        return userProfileRepository.findById(id).orElseThrow();
    }

    @CacheEvict( cacheNames = "cacheTest", key = "#userEntity.getIdx()")
    @Transactional
    public UserEntity cacheSave(UserEntity userEntity) {
        userProfileRepository.save(userEntity);
//        UserEntity userEntity = userProfileRepository.findById(id).orElseThrow();
//        userEntity.setName(name);
        return null;
//        return userEntity;
    }
}
