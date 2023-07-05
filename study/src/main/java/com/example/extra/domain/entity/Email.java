package com.example.extra.domain.entity;

import lombok.Data;

// MailDto 관련 entity
@Data
public class Email {

    private String receiver;
    private String title;
    private String content;
}
