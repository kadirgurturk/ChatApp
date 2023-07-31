package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.dto.UserDto;
import com.kadirgurturk.demo.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public List<UserDto> getAllUser()
    {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .map(UserDto::new)
                .collect(Collectors.toList());

    }


}
