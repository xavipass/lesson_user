package com.example.extra.domain.entity;

import com.example.extra.domain.dto.ChatMessageDto;
import com.example.extra.domain.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;


@Getter
public class ChatRoom {

    private String chatId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }


    public void handlerActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService) {
        if (chatMessageDto.getType().equals(ChatMessageDto.MessageType.ENTER)) {
            sessions.add(session);
            chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장 했습니다");
            //chatMessage.getSender() + "님이 입장했습니다."
        }
        sendMessage(chatMessageDto, chatService);
    }
    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }

}
