package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.User;

import java.util.List;

public interface UserServiceInterface {
    User saveUser(User user);
    User getUserById(Integer id);
    List<User> getUsers();
    User updateUser(Integer id, User user);
    User patchUpdateUser(Integer id, User user);
    User deleteUser(Integer id);
}
