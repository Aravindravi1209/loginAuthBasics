package com.arav.loginAuth.users;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        UserEntity userEntity = modelMapper.map(createUserRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return modelMapper.map(userRepository.save(userEntity), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserRequestDto loginUserRequestDto) {
        UserEntity userEntity = userRepository.findByUsername(loginUserRequestDto.getUsername());
        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }
        if(!passwordEncoder.matches(loginUserRequestDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }
        return modelMapper.map(userRepository.save(userEntity), UserResponseDto.class);
    }
}
