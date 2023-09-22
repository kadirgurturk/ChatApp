package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.User;
import lombok.Data;

@Data
public class UserDto {

    public String first_name;
    public Long userId;
    public boolean active;

    public UserDto(User entity)
    {

        first_name = entity.getFirstName();
        userId = entity.getId();
        active = entity.isActive();

    }

}
