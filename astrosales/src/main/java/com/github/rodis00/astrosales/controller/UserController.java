package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.dto.UserDto;
import com.github.rodis00.astrosales.exception.UserWithThisEmailExistException;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.service.UserProfileService;
import com.github.rodis00.astrosales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final UserProfileService userProfileService;
    @Autowired
    public UserController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if(userService.getUserByEmail(user.getEmail()) == null)
        {
            UserProfile newUserProfile = new UserProfile();
            User newUser = userService.saveUser(user);
            newUser.setUserProfile(newUserProfile);
            userProfileService.saveUserProfile(newUserProfile);
            userService.saveUser(newUser);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(UserDto.from(user));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new UserWithThisEmailExistException().getErrorDetail());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsers()
                        .stream()
                        .map(UserDto::from)
                        .toList()
                );
    }
}
