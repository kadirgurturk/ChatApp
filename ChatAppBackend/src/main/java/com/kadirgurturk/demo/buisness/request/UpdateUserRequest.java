package com.kadirgurturk.demo.buisness.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {


    @Size(min = 3)
    private String firstName;


    @Size(min = 3)
    private String lastName;


    @Size(min = 0, max = 30)
    private Integer avatarId;

}
