package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.dto.ChatDto;
import com.kadirgurturk.demo.buisness.request.ChatRequest;
import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.buisness.service.ChatService;
import com.kadirgurturk.demo.buisness.service.MessageService;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.enums.ChatType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat/")
public class ChatController {

    private UserService userService;

    private ChatService chatService;

    @PostMapping("/single")
    public ApıResponse<?> createChat(@RequestBody ChatRequest chatRequest)
    {

        ApıResponse<Chat> apıResponse = new ApıResponse<>();

        Chat chat = chatService.createChat(chatRequest.senderId,chatRequest.receiverId);

        apıResponse.setResults(chat);
        apıResponse.setStatus("Success");

        return  apıResponse;
    }





}
