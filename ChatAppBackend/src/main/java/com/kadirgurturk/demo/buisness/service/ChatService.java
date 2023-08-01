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
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;

    private UserRepository userRepository;

    private UserDto userDto;

    public ChatDto saveChat(ChatRequest chatRequest)
    {


        var chat = new Chat();

        if(chatRequest.chatType.equals(ChatType.PRIVATE)){


            chat.setType(ChatType.valueOf(chatRequest.getChatType()));
            var array = new ArrayList<User>();

            array.add(userRepository.findById(chatRequest.senderId).get());
            array.add(userRepository.findById(chatRequest.receiverId).get());

            chatRepository.save(chat);

        }else{


            chat.setType(ChatType.valueOf(chatRequest.getChatType()));
            var array = new ArrayList<User>();

            array.add(userRepository.findById(chatRequest.senderId).get());

            chatRepository.save(chat);

        }

        return new ChatDto(chat);
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

    public boolean existsPrivateChatWithUsers(ChatRequest request) {
        List<Long> userIds = Arrays.asList(request.senderId, request.receiverId);
        return chatRepository.existsByTypeAndUsers_IdIn(ChatType.PRIVATE, userIds);
    }

    public ChatDto findByTypeAndUsersIn(ChatRequest request)
    {

    }


}
