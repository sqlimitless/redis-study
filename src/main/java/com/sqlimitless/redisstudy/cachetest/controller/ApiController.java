package com.sqlimitless.redisstudy.cachetest.controller;

import com.sqlimitless.redisstudy.cachetest.dto.UserProfile;
import com.sqlimitless.redisstudy.cachetest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;

    @GetMapping("/user/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable("userId") String userId){
        return userService.getUserProfile(userId);
    }
}
