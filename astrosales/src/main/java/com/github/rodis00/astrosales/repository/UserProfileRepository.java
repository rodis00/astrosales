package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
