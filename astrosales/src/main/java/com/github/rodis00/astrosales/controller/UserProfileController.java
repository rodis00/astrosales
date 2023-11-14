package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.exception.UserProfileNotFoundException;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("astrosales/api/v1/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userProfileService.getUserProfileById(id));
        } catch (UserProfileNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfile) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userProfileService.updateUserProfile(id, userProfile));
        } catch (UserProfileNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfile) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userProfileService.patchUpdateUserProfile(id, userProfile));
        } catch (UserProfileNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }
}
