package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.repository.UserRepository;
import com.kadirgurturk.demo.exception.UserExcepiton;
import com.kadirgurturk.demo.security.JwtUserDetails;
import jakarta.transaction.Transactional;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServer implements UserDetailsService {

    private UserRepository userRepository;


    public UserDetailsServer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserExcepiton("User Not Found with user: "));


        return JwtUserDetails.create(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // We are using email instead userName,so we return email

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserExcepiton("User Not Found with user: "));

        return JwtUserDetails.create(user);
    }
}
