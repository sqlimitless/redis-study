package com.sqlimitless.redisstudy.cachetest.controller;

import com.sqlimitless.redisstudy.cachetest.dto.UserEntity;
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

    @GetMapping("/cache/{id}")
    public UserEntity chcheTest(@PathVariable("id") long id){
        System.out.println("ApiController.chcheTest");
        return userService.cacheFindById(id);
    }
//
//    @GetMapping("/cache/{id}/{name}")
//    public UserEntity chcheTest2(@PathVariable("id") long id, @PathVariable("name") String name){
//        System.out.println("ApiController.chcheTest");
//        System.out.println("id = " + id);
//        return userService.cacheSave(id,name);
//    }
}
