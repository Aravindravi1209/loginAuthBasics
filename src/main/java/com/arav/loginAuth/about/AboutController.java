package com.arav.loginAuth.about;

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
}
