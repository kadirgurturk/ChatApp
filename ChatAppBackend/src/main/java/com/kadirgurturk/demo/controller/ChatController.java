package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.buisness.service.MessageService;
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

    private MessageService messageService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageResponse receiveMessage(@Payload MessageResponse response){
        return messageService.saveMessage(response);
    }

    @MessageMapping("/private-message")
    public MessageResponse recMessage(@Payload MessageResponse response){
        //simpMessagingTemplate.convertAndSendToUser(response.getReceiver(),"/private",response);

        return messageService.saveMessage(response);
    }

}
