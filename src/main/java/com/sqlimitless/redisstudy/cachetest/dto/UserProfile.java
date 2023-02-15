package com.sqlimitless.redisstudy.cachetest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProfile(
        @JsonProperty String name,
        @JsonProperty int age) {
}
