package com.github.rodis00.astrosales.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;
}
