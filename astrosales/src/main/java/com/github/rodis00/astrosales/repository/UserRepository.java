package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
