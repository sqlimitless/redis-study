package com.sqlimitless.redisstudy.cachetest.infrastructer;


import com.sqlimitless.redisstudy.cachetest.dto.UserEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserEntity, Long> {


    @Override
    Optional<UserEntity> findById(Long id);
}
