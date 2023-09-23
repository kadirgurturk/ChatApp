package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.dto.AuthResponse;
import com.kadirgurturk.demo.buisness.request.RegisterRequest;
import com.kadirgurturk.demo.buisness.request.UserRequest;
import com.kadirgurturk.demo.buisness.service.ChatService;
import com.kadirgurturk.demo.buisness.service.UserDetailsServer;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.exception.UserExcepiton;
import com.kadirgurturk.demo.security.JwtTokenProvider;
import com.kadirgurturk.demo.security.JwtUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private ChatService chatService;

    private UserDetailsServer userDetailsServer;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, ChatService chatService, UserDetailsServer userDetailsServer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.chatService = chatService;
        this.userDetailsServer = userDetailsServer;
    }

    @PostMapping("/login")
    public ApıResponse<?> login(@RequestBody UserRequest loginRequest){
        AuthResponse authResponse = new AuthResponse();

        Authentication auth;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            //Here is the error
            throw new BadCredentialsException("Incorrect username or password", e);
        }
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

        if(userService.isEmailValid(registerRequest.getEmail())){
            throw new UserExcepiton("this email has already using");
        }

        User userNew = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .createdDate(LocalDateTime.now())
                .email(registerRequest.getEmail()).build();

        userNew.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        try {
            userService.saveUser(userNew);
        } catch (Exception e) {
           throw new UserExcepiton("We can't save the new User");
        }

        chatService.addUserChatRoom(userNew.getEmail()); // We added new User to chatroom

        UserDetails user = userDetailsServer.loadUserByUsername(userNew.getEmail());

        Authentication auth;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
        } catch (BadCredentialsException e) {
            //Here is the error
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateToken(auth);

        authResponse.setMessege("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);

        var apıResponse = new ApıResponse<AuthResponse>();

        apıResponse.setStatus("Succes");
        apıResponse.setResults(authResponse);

        return apıResponse;

    }

}
