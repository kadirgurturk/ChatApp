package com.kadirgurturk.demo.controller;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;
import com.kadirgurturk.demo.buisness.service.UserService;
import com.kadirgurturk.demo.data.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/user/")
public class UserController {

    UserService userService;

    @GetMapping("")
    public ApıResponse<?> searchUser(@RequestParam("query") String query){

        List<User> userList = userService.searchUser(query);

        var apıResponse = new ApıResponse<List<User>>();

        apıResponse.setStatus("Succes");
        apıResponse.setResults(userList);

        return apıResponse;

    }

}
