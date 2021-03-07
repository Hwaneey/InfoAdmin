package com.infognc.Administrator.Modules.Admin;

import com.infognc.Administrator.Infra.Config.PasswordConfig;
import com.infognc.Administrator.Infra.Config.SecurityConfig;
import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AccountRepository accountRepository;
    private final AdminRegisterForm adminRegisterForm;
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/accountList")
    public String getUsers(Model model,
                           @PageableDefault(size = 30,direction = Sort.Direction.DESC) Pageable pageable){

        List<Account> accounts = accountRepository.findAll();
        Page<Account> accountPage = accountRepository.findAll(pageable);

        model.addAttribute("accountPage",accountPage);
        model.addAttribute("accounts",accounts);

        return "admin/accountList";
    }


    @GetMapping("/admin/accountRegister")
    public String registerUser(){
        return "admin/accountRegister";
    }


    @PostMapping("/accountRegister")
    public String registerUser(AdminRegisterForm adminRegisterForm){

        Account account = modelMapper.map(adminRegisterForm, Account.class);

        account.setPassword(passwordEncoder.encode(adminRegisterForm.getPassword()));

        adminService.createUser(account);

        return "redirect:/admin/accountList";
    }
}
