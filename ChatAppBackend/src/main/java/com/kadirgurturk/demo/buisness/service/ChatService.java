package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.dto.ChatDto;
import com.kadirgurturk.demo.buisness.dto.MessageDto;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;
    private UserService userService;

    public Chat createChat(Long senderId, Long receiverId ){
        User sender = userService.findUserById(senderId);
        User reciever = userService.findUserById(receiverId);

        Chat isChatExist = chatRepository.findPrivateChatsByUsers(sender,reciever);

        if(isChatExist!=null){

            return isChatExist;
        }else {
            Chat chat = new Chat();
            chat.getUsers().add(sender);
            chat.getUsers().add(reciever);
            chat.setCreatedUser(sender);
            chat.setType(ChatType.PRIVATE);

            return chat;
        }

    }

    public Chat addUserChatRoom(Long userId){

        Chat chatRoom = chatRepository.findByType(ChatType.CHATROOM).get(0);

        User user = userService.findUserById(userId);

        chatRoom.getUsers().add(user);

        return chatRoom;
    }

    public Chat createRoom(Long senderId, Long receiverId ){

        Chat chatRoom = new Chat();

        chatRoom.setType(ChatType.PRIVATE);

        return chatRoom;


    }

    public Chat findChatById(Long id){
        Optional<Chat> chat = chatRepository.findById(id);

        if(chat.isPresent()){
            return chat.get();
        }else{
            // TODO added excepiton
        }

    }

    public List<Chat> findAllChatByUserId(Long id){

        return chatRepository.findByUsersId(id);
    }


}
