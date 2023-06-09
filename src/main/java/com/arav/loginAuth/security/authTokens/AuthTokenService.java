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
        if(userEntity == null)
            throw new IllegalArgumentException("User cannot be null");
        var token = new AuthTokenEntity();
        token.setUser(userEntity);
        authTokenRepository.save(token);
        return token.getToken().toString();
    }

    public UserEntity getUserFromToken(String token) {
        for(AuthTokenEntity authTokenEntity : authTokenRepository.findAll())
        {
            if(authTokenEntity.getToken().toString().equals(token))
            {
                return authTokenEntity.getUser();
            }
        }
        throw new IllegalArgumentException("Token not found");
    }
}
