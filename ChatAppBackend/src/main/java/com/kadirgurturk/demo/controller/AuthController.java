package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.dto.AuthResponse;
import com.kadirgurturk.demo.buisness.request.RegisterRequest;
import com.kadirgurturk.demo.buisness.request.UserRequest;
import com.kadirgurturk.demo.buisness.service.ChatService;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.exception.UserExcepiton;
import com.kadirgurturk.demo.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private ChatService chatService;


    @PostMapping("/login")
    public ApıResponse<?> login(@RequestBody UserRequest loginRequest){
        AuthResponse authResponse = new AuthResponse();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateToken(auth);

        User user = userService.findByEmail(loginRequest.getEmail());

        authResponse.setMessege("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setUserId(user.getId());

        var apıResponse = new ApıResponse<AuthResponse>();

        apıResponse.setStatus("Succes");
        apıResponse.setResults(authResponse);

        return apıResponse;

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
                .email(registerRequest.getEmail()).build();

        userNew.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        userService.saveUser(userNew);

        chatService.addUserChatRoom(userNew.getEmail()); // We added new User to chatroom

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userNew.getEmail(),userNew.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateToken(auth);

        authResponse.setMessege("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setUserId(userNew.getId());

        var apıResponse = new ApıResponse<AuthResponse>();

        apıResponse.setStatus("Succes");
        apıResponse.setResults(authResponse);

        return apıResponse;

    }

}
