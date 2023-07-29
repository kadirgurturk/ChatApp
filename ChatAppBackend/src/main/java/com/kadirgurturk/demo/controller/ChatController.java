package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.request.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ChatResponse receiveMessage(@Payload ChatResponse response){
        return response;
    }

    @MessageMapping("/private-message")
    public ChatResponse recMessage(@Payload ChatResponse response){
        simpMessagingTemplate.convertAndSendToUser(response.getReceiver(),"/private",response);

        return response;
    }

}
