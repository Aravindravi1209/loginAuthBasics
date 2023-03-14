package com.arav.loginAuth.users;

import com.arav.loginAuth.users.dtos.CreateUserRequestDto;
import com.arav.loginAuth.users.dtos.LoginUserRequestDto;
import com.arav.loginAuth.users.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        var createdUser =userService.createUser(createUserRequestDto);
        return ResponseEntity.created(URI.create("/users/"+createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> verifyUser(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        var verifiedUser =userService.verifyUser(loginUserRequestDto);
        return ResponseEntity.ok(verifiedUser);
    }

}
