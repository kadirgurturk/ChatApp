package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDto {

    private MessageStatus status;
    public String content;
    private MessageType type;
    private Long chatId;
    public Long senderId;
    public Long receiverId;


    public MessageDto(Message entity)
    {

        status = entity.getStatus();
        content = entity.getContent();
        type = entity.getType();
        chatId = entity.getChat().getId();
        senderId = entity.getChat().getUsers().get(0).getId();
        receiverId = entity.getChat().getUsers().get(1).getId();
    }


}
