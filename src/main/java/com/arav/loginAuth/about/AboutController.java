package com.arav.loginAuth.about;

import com.arav.loginAuth.users.dtos.UserResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/about")
public class AboutController
{
    @GetMapping("")
    public String about()
    {
        return "This is a simple login authentication application";
    }

    @GetMapping("/private")
    public String privateAbout(@AuthenticationPrincipal UserResponseDto user)
    {
        var username = user.getUsername();
        return "This is a private about page for authenticated users. You are viewing this as "+username;
    }
}
