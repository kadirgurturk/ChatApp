package com.kadirgurturk.demo.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private String message;
    private String sender;
    private String receiver;
    private MessageType status;


}
