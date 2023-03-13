package com.arav.loginAuth.users;

import com.arav.loginAuth.security.authTokens.AuthTokenService;
import com.arav.loginAuth.users.dtos.CreateUserRequestDto;
import com.arav.loginAuth.users.dtos.LoginUserRequestDto;
import com.arav.loginAuth.users.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenService authTokenService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
    }

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = modelMapper.map(createUserRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        var response = modelMapper.map(savedUser, UserResponseDto.class);
        var token = authTokenService.createToken(savedUser);
        response.setToken(token);
        return response;
    }

    public UserResponseDto verifyUser(LoginUserRequestDto loginUserRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(loginUserRequestDto.getUsername());
        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(loginUserRequestDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }

        var response = modelMapper.map(userEntity, UserResponseDto.class);
        response.setToken(authTokenService.createToken(userEntity));
        return response;
    }
}
