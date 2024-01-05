package com.dungnx.chatapp.controller;

import com.dungnx.chatapp.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    //if the client calls the API or endpoints such as /app/chat.sendMessage
    // then it will be mapped with method sendMessage()
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessagePojo) {
        return chatMessagePojo;
    }


    // for /app/chat.addUser, it will be mapped with the method addUser().
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessagePojo, SimpMessageHeaderAccessor headerAccessor) {

        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }
}