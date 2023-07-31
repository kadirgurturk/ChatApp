package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.Chat;
import com.kadirgurturk.demo.data.entity.Message;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.enums.ChatType;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ChatDto {

    private Long id;
    private ChatType type;
    private List<MessageDto> messageDtos;

    public ChatDto(Chat entity)
    {

        id = entity.getId();
        type = entity.getType();
        messageDtos = StreamSupport.stream(entity.getMessages().spliterator(),false)
                .map(MessageDto::new)
                .collect(Collectors.toList());

    }
}
