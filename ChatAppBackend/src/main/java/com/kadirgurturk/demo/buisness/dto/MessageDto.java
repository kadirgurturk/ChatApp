package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MessageDto {

    private MessageStatus status;
    public String content;
    private MessageType type;
    private Long chatId;

    public MessageDto(Message entity)
    {

        status = entity.getStatus();
        content = entity.getContent();
        type = entity.getType();
        chatId = entity.getChat().getId();

    }


}
