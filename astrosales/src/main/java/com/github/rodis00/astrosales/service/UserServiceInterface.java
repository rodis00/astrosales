package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Role;
import com.github.rodis00.astrosales.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    User saveUser(User user);
    User getUserById(Integer id);
    List<User> getUsers();
    User updateUser(Integer id, User user);
    User patchUpdateUser(Integer id, User user);
    void deleteUser(Integer id);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByRole(Role role);
}
