package com.sqlimitless.redisstudy.cacheTest;

import com.sqlimitless.redisstudy.cachetest.dto.UserEntity;
import com.sqlimitless.redisstudy.cachetest.infrastructer.UserProfileRepository;
import com.sqlimitless.redisstudy.cachetest.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTest {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("개싱된 데이터 확인해 보기")
    public void cacheTestCase1() {
        /**
         * given
         *
         * 기본으로 데이터 몇개를 넣어주고 있다고 가정한다.
         */
        UserEntity userEntity = userProfileRepository.save(
                UserEntity.builder()
                        .age(36)
                        .name("이훈재")
                        .build()
        );
        userProfileRepository.save(
                UserEntity.builder()
                        .age(21)
                        .name("홍길동")
                        .build()
        );
        userProfileRepository.save(
                UserEntity.builder()
                        .age(55)
                        .name("임꺽정")
                        .build()
        );
        /**
         * when
         *
         * 캐싱되는지 하나의 함수를 호출한다.
         */
        UserEntity userEntity1 = userService.cacheFindById(userEntity.getIdx());
        System.out.println("userEntity1 = " + userEntity1 + userEntity1.hashCode());
        /**
         * then
         *
         * 캐싱된 값을 불러오는지, DB에 붙어값을 가지고 오는지 확인해 본다.
         * 함수안에 로그가 기록되지 않은걸 보면 캐싱된 데이터를 불러온것으로 추청됨.
         */
        UserEntity userEntity2 = userService.cacheFindById(userEntity.getIdx());
        System.out.println("userEntity2 = " + userEntity2 + userEntity2.hashCode());

        Assertions.assertEquals(userEntity2.getIdx(),userEntity.getIdx());
    }

    @Test
    @DisplayName("리턴이 null인 함수를 통해 캐싱")
    public void cacheTestCase2() {
        /**
         * given
         *
         * 리턴이 null인 함수를 통해 캐싱
         */
        UserEntity userEntity =
                UserEntity.builder()
                        .age(36)
                        .name("이훈재")
                        .build();
        userService.cacheSave(userEntity);
        userService.cacheSave(
                UserEntity.builder()
                        .age(21)
                        .name("홍길동")
                        .build()
        );
        userService.cacheSave(
                UserEntity.builder()
                        .age(55)
                        .name("임꺽정")
                        .build()
        );
        /**
         * when
         *
         * 캐싱처리한 데이터의 값을 수정하고 호출
         */
        System.out.println(userService.cacheFindById(userEntity.getIdx()));
        userService.cacheSave(UserEntity.builder()
                .idx(userEntity.getIdx())
                .age(55)
                .name("zzz")
                .build());
        UserEntity userEntity1 = userService.cacheFindById(userEntity.getIdx());
        /**
         * then
         *
         * 변경된 값이 같은지 확인해 본다.
         */
        System.out.println("userEntity1 = " + userEntity1);

        /**
         * 결론: @CacheEvict을 사용하면 데이터 내용이 변경됀것을 인지하고 디비에서 가지고 오는거같다.
         * 어노테이션 @CacheEvict를 사용 안할경우엔 그냥 캐싱된 예전 데이터를 불러오는듯함.
         * 그리고 리턴값이 null이여도 엔티티를 기준으로 캐싱되는듯 함.
         * 왜인까..? 나중에 다시 테스트 해보자
         */

    }

}
