package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.request.MessageRequest;
import com.kadirgurturk.demo.buisness.service.MessageService;
import com.kadirgurturk.demo.data.entity.Message;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/messages/")
public class MessageController {

    private MessageService messageService;

    @GetMapping("send")
    public ApıResponse<?> sendMessage(@RequestBody @Valid MessageRequest messageRequest)
    {
        ApıResponse<Message> apıResponse = new ApıResponse<>();

        var message = messageService.sendMessage(messageRequest);

        apıResponse.setStatus("Success");
        apıResponse.setResults(message);

        return apıResponse;

    }
    @GetMapping("chat/")
    public ApıResponse<?> getChatMessages(@RequestParam("id") Long chatId)
    {
        ApıResponse<List<Message>> apıResponse = new ApıResponse<>();

        var message = messageService.getChatMessages(chatId);

        apıResponse.setStatus("Success");
        apıResponse.setResults(message);

        return apıResponse;

    }

    @GetMapping("")
    public ApıResponse<?> getById(@RequestParam("id") Long chatId)
    {
        ApıResponse<List<Message>> apıResponse = new ApıResponse<>();

        var message = messageService.getChatMessages(chatId);

        apıResponse.setStatus("Success");
        apıResponse.setResults(message);

        return apıResponse;

    }

}
