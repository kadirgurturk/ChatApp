package com.kadirgurturk.demo.buisness.request;

import lombok.Data;

@Data
public class MessageRequest {

    private Long chatId;
    private Long senderId;
    private String content;

}
