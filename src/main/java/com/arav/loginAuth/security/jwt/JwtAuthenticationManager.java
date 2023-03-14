package com.arav.loginAuth.security.jwt;

import com.arav.loginAuth.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class JwtAuthenticationManager implements AuthenticationManager {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthenticationManager(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JwtAuthentication)
        {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

            // TODO: crpto failure on jwt verification
            // TODO: check if jwt is expired

            var jwtString = jwtAuthentication.getCredentials();
            var username = jwtService.getUsernameFromJwt(jwtString);
            var user = userService.findUserbyUsername(username);

            jwtAuthentication.setUser(user);
            return jwtAuthentication;
        }
        return null;
    }
}
