package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ChatDto;
import com.kadirgurturk.demo.buisness.request.ChatRequest;
import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.buisness.service.ChatService;
import com.kadirgurturk.demo.buisness.service.MessageService;
import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.enums.ChatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private ChatService chatService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ResponseEntity<?> receiveMessage(@Payload MessageResponse response){

        messageService.saveMessage(response);

        return ResponseEntity.ok(chatService.findChatroom());
    }

    @MessageMapping("/getchat")
    @SendTo("/chatroom/chat")
    public ResponseEntity<?> getChatByIdAndType(@Payload ChatRequest request) {

        ChatDto chatDto;


        if (chatService.existsPrivateChatWithUsers(request)) {

            chatDto = chatService.findByTypeAndUsersIn(request);
        } else {

            chatDto = chatService.saveChat(request);
        }


        return ResponseEntity.ok(chatDto);
    }

    @MessageMapping("/private-message")
    public MessageResponse recMessage(@Payload MessageResponse response){
        simpMessagingTemplate.convertAndSendToUser(response.getRecieverId().toString(),"/private",response);

        return messageService.saveMessage(response);
    }





}
