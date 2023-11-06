package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.UserNotFoundException;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new UserNotFoundException(id));
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
            if (!user.getEmail().equals(""))
                oldUser.setEmail(user.getEmail());
            if (!user.getPassword().equals(""))
                oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        }
        return oldUser;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = getUserById(id);
        if (user != null)
            userRepository.delete(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.getByEmail(email);
        return user;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
