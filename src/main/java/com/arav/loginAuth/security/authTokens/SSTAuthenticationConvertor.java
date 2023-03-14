package com.arav.loginAuth.security.authTokens;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class SSTAuthenticationConvertor implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            var authToken = authHeader.substring(7);
            return new SSTAuthentication(authToken);
        }
        return null;
    }
}
