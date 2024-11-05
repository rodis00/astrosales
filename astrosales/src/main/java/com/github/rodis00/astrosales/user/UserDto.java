package com.github.rodis00.astrosales.user;

import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String email;
    private Role role;
    private UserProfile userProfile;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setUserProfile(user.getUserProfile());
        return userDto;
    }
}
