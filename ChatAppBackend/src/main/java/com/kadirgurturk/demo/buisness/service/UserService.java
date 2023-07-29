package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


}
