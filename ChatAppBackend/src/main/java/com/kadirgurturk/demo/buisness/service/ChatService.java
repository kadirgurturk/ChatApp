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
import com.kadirgurturk.demo.exception.UserExcepiton;
import jakarta.annotation.PostConstruct;
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


    @PostConstruct
    public void initializeChatroom() {
        // Chatroom tipindeki bir sohbeti kontrol et
        List<Chat> chatroomList = chatRepository.findByType(ChatType.CHATROOM);

        if (chatroomList.isEmpty()) {
            // Chatroom yoksa, bir tane oluşturun ve kaydedin
            Chat chatroom = new Chat();
            chatroom.setType(ChatType.CHATROOM);
            chatRepository.save(chatroom);
        }
    }

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

    public void addUserChatRoom(String email){

        Chat chatRoom = chatRepository.findByType(ChatType.CHATROOM).get(0);

        if(chatRoom == null) throw new UserExcepiton("Chatroom null");

        User user = userService.findByEmail(email);

        if(user == null) throw new UserExcepiton("User null");

        chatRoom.getUsers().add(user);

        chatRepository.save(chatRoom);
    }

    public void createChatRoom(Long senderId, Long receiverId ){

        Chat chatRoom = new Chat();

        chatRoom.setType(ChatType.PRIVATE);


    }

    public Chat findChatById(Long id){
        Optional<Chat> chat = chatRepository.findById(id);

        if(chat.isPresent()){
            return chat.get();
        }else{
            // TODO added excepiton

            return null;
        }

    }

    public List<Chat> findAllChatByUserId(Long id){

        return chatRepository.findByUsersId(id);
    }


}
