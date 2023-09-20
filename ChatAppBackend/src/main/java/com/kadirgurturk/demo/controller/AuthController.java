package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.dto.AuthResponse;
import com.kadirgurturk.demo.buisness.request.RegisterRequest;
import com.kadirgurturk.demo.buisness.request.UserRequest;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.exception.UserExcepiton;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    UserService userService;

    @PostMapping("/login")
    public ApıResponse<?> login(@RequestBody UserRequest loginRequest){

    }

    @PostMapping("/register")
    public ApıResponse<?> register(@RequestBody RegisterRequest registerRequest)
    {
        AuthResponse authResponse = new AuthResponse();

        if(userService.findByEmail(registerRequest.getEmail()) != null){
            throw new UserExcepiton("this email has already using");
        }

        User userNew = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail()).build();

        userService.saveUser(userNew);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userNew.getEmail(),userNew.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(auth);



    }

}
