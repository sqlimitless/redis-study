package com.sqlimitless.redisstudy.pub.controller;

import com.sqlimitless.redisstudy.pub.dto.PubDto;
import com.sqlimitless.redisstudy.pub.service.PubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PubController {

    private final PubService pubService;

    @GetMapping("/pub")
    public String pubTest(PubDto pubDto){
        return pubService.pubTest(pubDto);
    }
}
