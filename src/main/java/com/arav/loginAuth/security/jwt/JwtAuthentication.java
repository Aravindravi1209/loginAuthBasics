package com.arav.loginAuth.security.jwt;

import com.arav.loginAuth.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {
    private String jwtString;
    private UserResponseDto user;

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: not needed for now, unless we have different resources accessible for different roles
        return null;
    }

    @Override
    public String getCredentials() {
        // This is where the string/token that is used for authentication will be returned
        return jwtString;
    }

    @Override
    public Object getDetails() {
        // TODO: not needed for now
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        // This is where the user/client object will be returned who is trying to authenticate
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user!=null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
