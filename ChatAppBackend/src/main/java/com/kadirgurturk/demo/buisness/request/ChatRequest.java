package com.kadirgurturk.demo.buisness.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kadirgurturk.demo.data.enums.ChatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatRequest {

    @NotNull
    @NotEmpty
    @Min(value = -1)
    public Long senderId;

    @Min(value = -1)
    @NotNull
    @NotEmpty
    public Long receiverId;

}
