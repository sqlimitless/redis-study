package com.sqlimitless.redisstudy.stringredis.presentation;

import com.sqlimitless.redisstudy.stringredis.applications.StringRedisApplications;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController()
public class StringRedisController {

    private final StringRedisApplications stringRedisApplications;

    @PostMapping("/{key}/{value}")
    public String setStringData(@PathVariable String key, @PathVariable String value) {
        return stringRedisApplications.setStringData(key, value);
    }

    @GetMapping("/{key}")
    public String getStringData(@PathVariable String key) {
        return stringRedisApplications.getStringData(key);
    }
}
