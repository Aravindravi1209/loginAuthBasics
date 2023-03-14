package com.arav.loginAuth.security.authTokens;

import com.arav.loginAuth.security.jwt.JwtService;
import com.arav.loginAuth.users.UserRepository;
import com.arav.loginAuth.users.UserService;
import com.arav.loginAuth.users.dtos.CreateUserRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJpaTest
public class AuthTokenServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthTokenRepository authTokenRepository;
    private JwtService jwtService;

    private UserService userService;
    private AuthTokenService authTokenService;

    @BeforeEach
    public void setup()
    {
        authTokenService = new AuthTokenService(authTokenRepository, userRepository);
        jwtService = new JwtService();
        userService = new UserService(userRepository,new ModelMapper(),
                new BCryptPasswordEncoder(), authTokenService,jwtService);
    }

    @Test
    void createToken_works_with_user()
    {
        userService.createUser(new CreateUserRequestDto("arav",
                "arav@yahoo.com", "password"));
        var userEntity = userRepository.findByUsername("arav");
        var token = authTokenService.createToken(userEntity);
    }

    @Test
    void createToken_error_with_null()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var token = authTokenService.createToken(null);
        });
    }

}
