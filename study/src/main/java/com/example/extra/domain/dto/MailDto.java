package com.example.extra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 로그인 실패시 인증번호 전송
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String address;

    private String title;

    private String message;
}
