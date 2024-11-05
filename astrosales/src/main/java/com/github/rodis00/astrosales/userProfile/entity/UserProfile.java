package com.github.rodis00.astrosales.userProfile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
