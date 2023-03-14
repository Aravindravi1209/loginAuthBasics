package com.arav.loginAuth.security;

import com.arav.loginAuth.security.jwt.JwtAuthenticationConverter;
import com.arav.loginAuth.security.jwt.JwtAuthenticationFilter;
import com.arav.loginAuth.security.jwt.JwtAuthenticationManager;
import com.arav.loginAuth.security.jwt.JwtService;
import com.arav.loginAuth.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JwtService jwtService, UserService userService) {
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(new JwtAuthenticationManager(jwtService, userService));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.cors().disable().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,"/about").permitAll()
                .requestMatchers(HttpMethod.POST,"/users","/users/login").permitAll()
                .requestMatchers("/*/**").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
