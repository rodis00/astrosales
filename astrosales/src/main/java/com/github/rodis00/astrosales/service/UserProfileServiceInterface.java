package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.UserProfile;

import java.util.List;

public interface UserProfileServiceInterface {
    UserProfile saveUserProfile(UserProfile userProfile);
    UserProfile getUserProfileById(Integer id);
    List<UserProfile> getUsersProfile(Integer id);
    UserProfile updateUserProfile(Integer id, UserProfile userProfile);
    UserProfile patchUpdateUserProfile(Integer id, UserProfile userProfile);
}
