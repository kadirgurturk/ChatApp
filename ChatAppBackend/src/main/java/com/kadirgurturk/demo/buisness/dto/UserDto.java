package com.kadirgurturk.demo.buisness.dto;

import com.kadirgurturk.demo.data.entity.User;
import lombok.Data;

@Data
public class UserDto {

    public String name;
    public Long userId;
    public boolean active;

    public UserDto(User entity)
    {
        name = entity.getName();
        userId = entity.getId();
        active = entity.isActive();

    }

}
