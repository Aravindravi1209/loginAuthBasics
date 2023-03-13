package com.arav.loginAuth.security.authTokens;

import com.arav.loginAuth.users.UserEntity;
import com.arav.loginAuth.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    private final AuthTokenRepository authTokenRepository;
    private final UserRepository userRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository, UserRepository userRepository) {
        this.authTokenRepository = authTokenRepository;
        this.userRepository = userRepository;
    }

    public String createToken(UserEntity userEntity) {
        var token = new AuthTokenEntity();
        token.setUser(userEntity);
        authTokenRepository.save(token);
        return token.getToken().toString();
    }

    public UserEntity getUserFromToken(String token) {
        var authToken = authTokenRepository.findById(token).orElseThrow();
        return authToken.getUser();
    }
}
