package com.arav.loginAuth.users;

import com.arav.loginAuth.security.authTokens.AuthTokenService;
import com.arav.loginAuth.security.jwt.JwtService;
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

    private final JwtService jwtService;
    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder, AuthTokenService authTokenService,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
        this.jwtService = jwtService;
    }

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = modelMapper.map(createUserRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        var response = modelMapper.map(savedUser, UserResponseDto.class);
        //Option 1: Server Side Token
        //var token = authTokenService.createToken(savedUser);
        //response.setToken(token);

        //Option 2: JWT Token
        var token = jwtService.createJwt(savedUser.getUsername());
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
        response.setToken(jwtService.createJwt(userEntity.getUsername()));
        return response;
    }
}
