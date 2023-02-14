package com.sqlimitless.redisstudy.sessiontest.presentation;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    /**
     * 레디스 세션 값 테스트
     * 1. 서로다른 was를 띄워서 세션아이디 확인
     * 2. implementation("org.springframework.session:spring-session-data-redis")
     *    의존성추가하고 다시 서로다른 was띄워서 세션아이디 확인
     * @param session
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession session){
        return session.getId();
    }
}
