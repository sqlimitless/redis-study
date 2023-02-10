package com.sqlimitless.redisstudy.stringredis.applications;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StringRedisApplications {

    private final StringRedisTemplate stringRedisTemplate;

    public String setStringData(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
        return key + ": " + ops.get(key);
    }

    public String getStringData(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }
}
