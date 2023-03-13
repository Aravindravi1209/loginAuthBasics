package com.arav.loginAuth.users;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String username;
    private String password;
}
