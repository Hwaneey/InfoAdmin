package com.infognc.Administrator.Modules.Account;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller @RequiredArgsConstructor
public class AccountController {

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }
}
