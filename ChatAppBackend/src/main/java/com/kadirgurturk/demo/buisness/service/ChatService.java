package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.dto.ChatDto;
import com.kadirgurturk.demo.buisness.dto.UserDto;
import com.kadirgurturk.demo.buisness.request.ChatRequest;
import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.enums.ChatType;
import com.kadirgurturk.demo.data.repository.ChatRepository;
import com.kadirgurturk.demo.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;

    private UserRepository userRepository;

    private UserDto userDto;

    public ChatRequest saveChat(ChatRequest chatRequest)
    {
        if(chatRequest.chatType.equals(ChatType.PRIVATE)){

            var chat = new Chat();

            chat.setType(chatRequest.chatType);
            var array = new ArrayList<User>();

            array.add(userRepository.findById(chatRequest.senderId).get());
            array.add(userRepository.findById(chatRequest.receiverId).get());

            chatRepository.save(chat);

        }else{

            var chat = new Chat();

            chat.setType(chatRequest.chatType);
            var array = new ArrayList<User>();

            array.add(userRepository.findById(chatRequest.senderId).get());

            chatRepository.save(chat);

        }

        return chatRequest;
    }

    public void addUserToChatroom(UserDto userDto)
    {
        var chatroom = chatRepository.findByType(ChatType.CHATROOM);
        var user = userRepository.findById(userDto.userId);
        if(user.isPresent()){

        }
    }

    public ChatDto findChatById(Long chatId)
    {
        var chat = chatRepository.findById(chatId);

        if(chat.isEmpty()){

        }

        return new ChatDto(chat.get());

    }
}
