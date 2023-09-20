package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.repository.UserRepository;
import com.kadirgurturk.demo.exception.UserExcepiton;
import com.kadirgurturk.demo.security.JwtUserDetails;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServer {

    private UserRepository userRepository;


    public UserDetails loadUserByEmail(String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserExcepiton("User Not Found with this email: "));

        return JwtUserDetails.create(user);
    }


    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserExcepiton("User Not Found with user: "));


        return JwtUserDetails.create(user);
    }
}
