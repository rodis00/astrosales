package com.github.rodis00.astrosales.userProfile;

import com.github.rodis00.astrosales.userProfile.exception.UserProfileNotFoundException;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile saveUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile getUserProfileById(Integer id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found."));
    }

    public List<UserProfile> getUsersProfile(Integer id) {
        return userProfileRepository.findAll();
    }

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
}
