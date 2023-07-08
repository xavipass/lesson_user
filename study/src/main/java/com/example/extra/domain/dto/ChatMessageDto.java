package com.example.extra.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class ChatMessageDto {

    public enum MessageType{
        ENTER, TALK
    }

    private TrayIcon.MessageType type;
    private String chatId;
    private String sender;
    private String message;
}
