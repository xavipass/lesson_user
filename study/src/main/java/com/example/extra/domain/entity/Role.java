package com.example.extra.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 권한에 따른 기능 허가
@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String key;
}
