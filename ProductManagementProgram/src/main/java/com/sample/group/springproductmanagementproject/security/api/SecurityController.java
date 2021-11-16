package com.sample.group.springproductmanagementproject.security.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SecurityController {

    @GetMapping("/api/security/login")
    public String getLogin() {
        return "login";
    }
}
