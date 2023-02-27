package com.sqlimitless.redisstudy.cachetest.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tUser")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idx;

    private String name;
    private int age;

    @Builder
    public UserEntity(long idx, String name, int age) {
        this.idx = idx;
        this.name = name;
        this.age = age;
    }
}
