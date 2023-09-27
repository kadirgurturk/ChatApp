package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.dto.UserDto;
import com.kadirgurturk.demo.buisness.request.MessageRequest;
import com.kadirgurturk.demo.buisness.request.UpdateUserRequest;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.User;
import com.kadirgurturk.demo.exception.UserExcepiton;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user/")
public class UserController {

    UserService userService;

    @GetMapping()
    public ApıResponse<?> searchUser(@RequestParam("query") String query){

        List<User> userList = userService.searchUser(query);

        var apıResponse = new ApıResponse<List<User>>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(userList);

        return apıResponse;

    }
    @GetMapping("users")
    public ApıResponse<?> getAllUser(){

        List<UserDto> userList = userService.getAllUser();

        var apıResponse = new ApıResponse< List<UserDto>>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(userList);

        return apıResponse;

    }

    @GetMapping("user/")
    public ApıResponse<?> findById(@RequestParam("id") Long id){

        User user = userService.findUserById(id);

        var apıResponse = new ApıResponse<User>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(user);

        return apıResponse;

    }

    @GetMapping("user/")
    public ApıResponse<?> findById(@RequestParam("email") String email){

        User user = userService.findByEmail(email);

        var apıResponse = new ApıResponse<User>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(user);

        return apıResponse;

    }

    @PutMapping("update/")
    public ApıResponse<?> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest,@RequestParam("id") Long id){

        User user = userService.updateUser(id,updateUserRequest);

        var apıResponse = new ApıResponse<User>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(user);

        return apıResponse;

    }

    @PutMapping("update/")
    public ApıResponse<?> updateActive(@RequestParam("active") String active ,@RequestParam("email") String email){

        var val = active.toUpperCase(Locale.ROOT);
        var bool = val.equals("TRUE") ? true : false;

        User user = userService.setActive(email,bool);

        var apıResponse = new ApıResponse<User>();

        apıResponse.setStatus("Success");
        apıResponse.setResults(user);

        return apıResponse;

    }

}
