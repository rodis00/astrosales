package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.Role;
import com.github.rodis00.astrosales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Optional<User> findByRole(Role role);
}
