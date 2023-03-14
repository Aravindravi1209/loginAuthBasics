package com.arav.loginAuth.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtServiceTests {
    private JwtService jwtService()
    {
        return new JwtService();
    }
    @Test
    public void createJwt_works_with_username()
    {
        var jwtService = jwtService();
        var jwt = jwtService.createJwt("arav");
        var username = jwtService.getUsernameFromJwt(jwt);
        assertEquals("arav", username);
    }

    @Test
    public void createJwt_error_with_null()
    {
        var jwtService = jwtService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var jwt = jwtService.createJwt(null);
        });
    }
}
