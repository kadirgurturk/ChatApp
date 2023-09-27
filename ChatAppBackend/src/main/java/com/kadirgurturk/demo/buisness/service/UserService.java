package com.kadirgurturk.demo.buisness.service;

import com.kadirgurturk.demo.buisness.dto.UserDto;
import com.kadirgurturk.demo.buisness.request.UpdateUserRequest;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.data.repository.UserRepository;
import com.kadirgurturk.demo.exception.UserExcepiton;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    @Transactional
    public void saveUser(User user){
         userRepository.save(user);
    }


    public List<UserDto> getAllUser()
    {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .map(UserDto::new)
                .collect(Collectors.toList());

    }

    public User findUserById(Long id){

        var user = userRepository.findById(id);

        if (user.isPresent()){
            return user.get();
        }else{
            throw new UserExcepiton("This User is not valid");
        }
    }

    public UserDto findUserDtoById(Long id){

        return null;

    }

    public boolean isEmailValid(String email){
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email){

        var user = userRepository.findByEmail(email);

        if(user.isPresent()){
            return user.get();
        }else{
            throw new UserExcepiton("this email is not valid");
        }
    }

    public User setActive(String email, boolean active)
    {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()){
            throw new UserExcepiton("This User is not valid");
        }

        user.get().setActive(active);

        userRepository.save(user.get());

        return user.get();

    }

    public User updateUser(Long id, UpdateUserRequest updateUserRequest){
        var user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserExcepiton("This User is not valid");
        }

        user.get().setAvatarId(updateUserRequest.getAvatarId());
        user.get().setFirstName(updateUserRequest.getFirstName());
        user.get().setLastName(updateUserRequest.getLastName());

        userRepository.save(user.get());

        return user.get();

    }

    public List<User> searchUser(String query){

        return userRepository.searchUser(query);
    }


}
