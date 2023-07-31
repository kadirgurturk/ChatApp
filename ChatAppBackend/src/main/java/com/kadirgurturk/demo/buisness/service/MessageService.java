package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.dto.MessageDto;
import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.repository.ChatRepository;
import com.kadirgurturk.demo.data.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    private ChatRepository chatRepository;


    public MessageResponse saveMessage(MessageResponse messageResponse)
    {

        var chat = chatRepository.findById(messageResponse.getChatId());

        if(chat.isPresent()) {
            var message = new Message();

            message.setContent(messageResponse.getContent());
            message.setStatus(messageResponse.getStatus());
            message.setType(messageResponse.getType());
            message.setChat(chat.get());
        }

        return messageResponse;
    }



}
