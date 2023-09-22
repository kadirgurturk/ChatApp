package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.request.MessageRequest;
import com.kadirgurturk.demo.buisness.service.MessageService;
import com.kadirgurturk.demo.data.entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/messages/")
public class MessageController {

    private MessageService messageService;

    @GetMapping("send")
    public ApıResponse<?> sendMessage(@RequestBody MessageRequest messageRequest)
    {
        ApıResponse<Message> apıResponse = new ApıResponse<>();

        var message = messageService.sendMessage(messageRequest);

        apıResponse.setStatus("Succes");
        apıResponse.setResults(message);

        return apıResponse;

    }

}
