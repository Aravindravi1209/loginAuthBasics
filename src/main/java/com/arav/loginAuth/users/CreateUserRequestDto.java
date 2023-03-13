package com.arav.loginAuth.users;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    private String username;
    private String email;
    private String password;
}
