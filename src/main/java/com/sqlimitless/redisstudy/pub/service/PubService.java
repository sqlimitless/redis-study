package com.sqlimitless.redisstudy.pub.service;

import com.sqlimitless.redisstudy.pub.dto.PubDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PubService {

    private final StringRedisTemplate stringRedisTemplate;

    public String pubTest(PubDto pubDto){
        HashMap<String, String> map = new HashMap<>();
        map.put("name",pubDto.name());
        map.put("age",String.valueOf(pubDto.age()));
        map.put("memo",pubDto.memo());
        stringRedisTemplate.opsForStream().add("pubsubtest",map);
        System.out.println("map = " + map);
        return "ok";
    }
}
