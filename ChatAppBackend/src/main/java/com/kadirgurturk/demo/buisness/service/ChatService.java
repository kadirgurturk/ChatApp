package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.request.MessageResponse;
import com.kadirgurturk.demo.data.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;


}
