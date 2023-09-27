package com.kadirgurturk.demo.buisness.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min = 0, max = 30)
    private Integer avatarId;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Email
    private String email;
}
