package com.arav.loginAuth.security.authTokens;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class SSTAuthenticationFilter extends AuthenticationFilter {
    public SSTAuthenticationFilter(SSTAuthenticationManager authenticationManager) {
        super(authenticationManager, new SSTAuthenticationConvertor());
        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
}
