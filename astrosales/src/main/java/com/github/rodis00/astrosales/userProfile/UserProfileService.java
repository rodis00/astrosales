package com.github.rodis00.astrosales.userProfile;

import com.github.rodis00.astrosales.user.UserRepository;
import com.github.rodis00.astrosales.user.entity.User;
import com.github.rodis00.astrosales.user.exception.UserNotFoundException;
import com.github.rodis00.astrosales.userProfile.exception.UserProfileNotFoundException;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfileService(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
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
            if (isFieldNotBlankAndNotNull(userProfile.getFirstName()))
                oldUserProfile.setFirstName(userProfile.getFirstName());
            if (isFieldNotBlankAndNotNull(userProfile.getLastName()))
                oldUserProfile.setLastName(userProfile.getLastName());
            if (isFieldNotBlankAndNotNull(userProfile.getPhoneNumber()))
                oldUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
            userProfileRepository.save(oldUserProfile);
        }
        return oldUserProfile;
    }

    private boolean isFieldNotBlankAndNotNull(String field) {
        return field != null && !field.isBlank();
    }
}
