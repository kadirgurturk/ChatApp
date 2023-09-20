package com.kadirgurturk.demo.buisness.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String password;

    private String email;
}
