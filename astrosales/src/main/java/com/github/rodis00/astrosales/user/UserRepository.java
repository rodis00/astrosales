package com.github.rodis00.astrosales.user;

import com.github.rodis00.astrosales.user.entity.Role;
import com.github.rodis00.astrosales.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByRole(Role role);
}
