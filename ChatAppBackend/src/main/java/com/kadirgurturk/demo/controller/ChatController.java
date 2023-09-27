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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/chat/")
public class ChatController {

    private UserService userService;

    private ChatService chatService;

    @PostMapping("single")
    public ApıResponse<?> createChat(@RequestBody @Valid ChatRequest chatRequest)
    {

        ApıResponse<Chat> apıResponse = new ApıResponse<>();

        Chat chat = chatService.createChat(chatRequest.senderId,chatRequest.receiverId);

        apıResponse.setResults(chat);
        apıResponse.setStatus("Success");

        return  apıResponse;
    }

    @GetMapping("single/chat/")
    public ApıResponse<?> getChatController(Long userId)
    {

        ApıResponse<String> apıResponse = new ApıResponse<>();


        apıResponse.setResults("ChatController Open");
        apıResponse.setStatus("Success");

        return  apıResponse;
    }

    @GetMapping("single/user/")
    public ApıResponse<?> getAllChatList(@RequestParam("userid") Long userId)
    {

        ApıResponse<List<Chat>> apıResponse = new ApıResponse<>();

        List<Chat> chats = chatService.findAllChatByUserId(userId);

        apıResponse.setResults(chats);
        apıResponse.setStatus("Success");

        return  apıResponse;
    }

    @GetMapping("single/")
    public ApıResponse<?> getChatById(@RequestParam("chatid") Long chatId)
    {

        ApıResponse<Chat> apıResponse = new ApıResponse<>();

        Chat chat = chatService.findChatById(chatId);

        apıResponse.setResults(chat);
        apıResponse.setStatus("Success");

        return  apıResponse;
    }


}
