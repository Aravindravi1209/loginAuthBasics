package com.arav.loginAuth.users.dtos;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String username;
    private String password;
}
