package com.warehausesystem.app.warehauseSystemWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/logged")
    public String login()
    {
        return ("redirect:http://localhost:4200");
    }
}
