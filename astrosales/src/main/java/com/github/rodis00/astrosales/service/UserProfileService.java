package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.UserProfileNotFoundException;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService implements UserProfileServiceInterface{
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserProfileById(Integer id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException(id));
    }

    @Override
    public List<UserProfile> getUsersProfile(Integer id) {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile updateUserProfile(Integer id, UserProfile userProfile) {
        UserProfile oldUserProfile = getUserProfileById(id);
        if (userProfile != null) {
            oldUserProfile.setFirstName(userProfile.getFirstName());
            oldUserProfile.setLastName(userProfile.getLastName());
            oldUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
            userProfileRepository.save(oldUserProfile);
        }
        return oldUserProfile;
    }

    @Override
    public UserProfile patchUpdateUserProfile(Integer id, UserProfile userProfile) {
        UserProfile oldUserProfile = getUserProfileById(id);
        if (userProfile != null) {
            if (userProfile.getFirstName() != null)
                oldUserProfile.setFirstName(userProfile.getFirstName());
            if (userProfile.getLastName() != null)
                oldUserProfile.setLastName(userProfile.getLastName());
            if (userProfile.getPhoneNumber() != null)
                oldUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
            userProfileRepository.save(oldUserProfile);
        }
        return oldUserProfile;
    }

    @Override
    public UserProfile deleteUserProfile(Integer id) {
        UserProfile userProfile = getUserProfileById(id);
        userProfileRepository.delete(userProfile);
        return userProfile;
    }
}
