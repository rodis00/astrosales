package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.UserNotFoundException;
import com.github.rodis00.astrosales.model.Role;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, User user) {
        User oldUser = getUserById(id);
        if (oldUser != null) {
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        }
        return oldUser;
    }

    @Override
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

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        if (user != null)
            userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }
}
