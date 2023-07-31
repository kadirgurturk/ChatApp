package com.kadirgurturk.demo.buisness.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kadirgurturk.demo.data.enums.ChatType;

public class ChatRequest {

    private ChatType type;
    public Long senderId;
    public Long receiverId;
    public ChatType chatType;
}
