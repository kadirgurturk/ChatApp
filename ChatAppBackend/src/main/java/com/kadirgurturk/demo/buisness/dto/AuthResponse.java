package com.kadirgurturk.demo.buisness.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    String messege;
    Long userId;
    String accessToken;
    String refreshToken;

}
