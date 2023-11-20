package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
