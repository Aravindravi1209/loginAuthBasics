package com.arav.loginAuth.security.authTokens;

import com.arav.loginAuth.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.Collection;

public class SSTAuthentication implements Authentication {

    private String SSTString;

    private UserResponseDto user;

    public SSTAuthentication(String SSTString) {
        this.SSTString = SSTString;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return SSTString;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
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
