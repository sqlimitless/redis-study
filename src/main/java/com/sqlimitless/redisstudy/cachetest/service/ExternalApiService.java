package com.sqlimitless.redisstudy.cachetest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExternalApiService {

    public String getUserName(String userId){
        /*외부 서비스나 DB호출 한다고 가정*/
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        log.info("Getting userProfile from Service ...");

        if(userId.equals("A")){
            return "Adam";
        }
        if(userId.equals("B")){
            return "Bob";
        }
        return "";
    }

    @Cacheable(cacheNames = "userAgeCache", key = "#userId")
    public int getUserAge(String userId){
        /*외부 서비스나 DB호출 한다고 가정*/
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        log.info("Getting userProfile from Service ...");
        if(userId.equals("A")){
            return 36;
        }
        if(userId.equals("B")){
            return 26;
        }
        return 0;
    }
}
