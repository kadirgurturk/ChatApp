package com.kadirgurturk.demo.buisness.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MessageRequest {

    @NotNull
    @NotEmpty
    @Min(value = -1)
    private Long chatId;

    @NotNull
    @NotEmpty
    @Min(value = -1)
    private Long senderId;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String content;

}
