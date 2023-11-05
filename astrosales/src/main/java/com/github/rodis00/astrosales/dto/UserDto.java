package com.github.rodis00.astrosales.dto;

import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.model.UserProfile;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private UserProfile userProfile;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUserProfile(user.getUserProfile());
        return userDto;
    }
}
