package com.arav.loginAuth.users.dtos;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String token;

}
