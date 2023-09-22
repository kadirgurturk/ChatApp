package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.enums.MessageStatus;
import lombok.Data;

@Data
public class MessageDto {

    private MessageStatus status;
    public String content;
    private Long chatId;

    public MessageDto(Message entity)
    {

        status = entity.getStatus();
        content = entity.getContent();
        chatId = entity.getChat().getId();

    }


}
