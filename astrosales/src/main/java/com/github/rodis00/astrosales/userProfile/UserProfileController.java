package com.github.rodis00.astrosales.userProfile;

import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.getUserProfileById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfile) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.updateUserProfile(id, userProfile));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUpdateUserProfile(@PathVariable Integer id, @RequestBody UserProfile userProfile) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.patchUpdateUserProfile(id, userProfile));
    }
}
