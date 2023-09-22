package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.request.MessageRequest;
import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    public Message sendMessage(MessageRequest messageRequest)
    {

        User sender = userService.findUserById(messageRequest.getSenderId());
        Chat chat = chatService.findChatById(messageRequest.getSenderId());

        var newMessage = new Message();

        newMessage.setStatus(MessageStatus.MESSAGE);
        newMessage.setCreatedDate(LocalDateTime.now());
        newMessage.setChat(chat);
        newMessage.setSender(sender);

        return newMessage;

    }

    public List<Message> getChatMessages(Long chatId){

        return messageRepository.findByChatId(chatId);

    }

    public Message getById(Long id){

        return messageRepository.findById(id).get();

    }


    public void deleteById(Long id){

         messageRepository.deleteById(id);

    }

}
