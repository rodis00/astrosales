package com.github.rodis00.astrosales.user;

import com.github.rodis00.astrosales.user.exception.UserNotFoundException;
import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User user) {
        User oldUser = getUserById(id);
        if (oldUser != null) {
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        }
        return oldUser;
    }

    public User patchUpdateUser(Integer id, User user) {
        User oldUser = getUserById(id);
        if (oldUser != null) {
            if (user.getEmail() != null)
                oldUser.setEmail(user.getEmail());
            if (user.getPassword() != null)
                oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        }
        return oldUser;
    }

    public void deleteUser(Integer id) {
        User user = getUserById(id);
        if (user != null)
            userRepository.delete(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
