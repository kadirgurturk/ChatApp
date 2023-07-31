package com.kadirgurturk.demo.buisness.request;

import com.kadirgurturk.demo.data.enums.MessageStatus;
import com.kadirgurturk.demo.data.enums.MessageType;
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
    private MessageType type;
    private Long chatId;


}
