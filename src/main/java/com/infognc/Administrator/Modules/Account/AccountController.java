package com.infognc.Administrator.Modules.Account;


import com.infognc.Administrator.Modules.Admin.AdminRegisterForm;
import com.infognc.Administrator.Modules.Admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller @RequiredArgsConstructor
public class AccountController {


    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/account/profile")
    public String getUpdateProfileForm(@AuthenticationPrincipalAccount Account account, Model model) {
        model.addAttribute("account",account);
        return "account/profile";
    }

    @PostMapping("/account/profile")
    public String UpdateProfileForm(@AuthenticationPrincipalAccount Account account, Model model){

        return "";
    }


}
