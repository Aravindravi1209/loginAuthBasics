package com.arav.loginAuth.security.authTokens;

import com.arav.loginAuth.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity(name = "auth_tokens")
@Data
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    @ManyToOne
    private UserEntity user;
}
