package com.arav.loginAuth.security.authTokens;

import com.arav.loginAuth.users.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SSTAuthenticationManager implements AuthenticationManager {

    private final AuthTokenService authTokenService;
    private final UserService userService;

    public SSTAuthenticationManager(AuthTokenService authTokenService, UserService userService) {
        this.authTokenService = authTokenService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(authentication instanceof SSTAuthentication) {
            SSTAuthentication sstAuthentication = (SSTAuthentication) authentication;
            var authToken = sstAuthentication.getCredentials();
            var username = authTokenService.getUserFromToken(authToken).getUsername();
            var user = userService.findUserbyUsername(username);
            sstAuthentication.setUser(user);
            return sstAuthentication;
        }
        return null;
    }
}
