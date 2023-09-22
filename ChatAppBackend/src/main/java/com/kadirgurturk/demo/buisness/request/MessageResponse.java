package com.kadirgurturk.demo.buisness.request;

import com.kadirgurturk.demo.data.enums.MessageStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private String message;
    private String content;
    private MessageStatus status;
    private Long senderId;
    private Long recieverId;
    private Long chatId;



}
