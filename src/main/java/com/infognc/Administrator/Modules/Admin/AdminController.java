package com.infognc.Administrator.Modules.Admin;

import com.infognc.Administrator.Infra.Config.PasswordConfig;
import com.infognc.Administrator.Infra.Config.SecurityConfig;
import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Account.AccountService;
import com.infognc.Administrator.Modules.Account.UserSpecification;
import com.infognc.Administrator.Modules.Role.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/accountList")
    public String getUsers(Model model,
                           @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable) {

        List<Account> accounts = accountRepository.findAll();
        Page<Account> accountPage = accountRepository.findAll(pageable);

        model.addAttribute("accountPage", accountPage);
        model.addAttribute("accounts", accounts);

        return "admin/accountList";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam(value = "agentId") String agentId, Model model) {

        List<Account> accountPage = accountRepository.findByAgentIdContaining(agentId);
        model.addAttribute("accountPage", accountPage);
        return "admin/accountSearch";
    }

//    @GetMapping("/test/search")
//    public String testSearch(@PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable
//            , Map filter, Model model) {
//
//        Page<Account> accountPage = accountRepository.findAll(UserSpecification.searchUser(filter),pageable);
//        model.addAttribute("accountPage", accountPage);
//        return "test/accountSearch";
//    }

    @GetMapping("/admin/accountRegister")
    public String getRegisterUser(Model model) {

        List<Role> roleList = adminService.getRoles();

        model.addAttribute("roleList", roleList);
        return "admin/accountRegister";
    }


    @PostMapping("/accountRegister")
    public String registerUser(AdminRegisterForm adminRegisterForm) {

        Account account = modelMapper.map(adminRegisterForm, Account.class);
        account.setPassword(passwordEncoder.encode(adminRegisterForm.getPassword()));
        adminService.createUser(account, adminRegisterForm.getLevel());

        return "redirect:/admin/accountList";
    }

    @GetMapping("/admin/accountUpdate/{id}")
    public String getUpdateUser(@PathVariable(value = "id") Long id, Model model) {

        AdminRegisterForm adminRegisterForm = adminService.getUser(id);
        List<Role> roleList = adminService.getRoles();

        model.addAttribute("account", adminRegisterForm);
        model.addAttribute("roleList", roleList);

        return "admin/accountUpdate";
    }

    @PostMapping("/admin/accountUpdate")
    public String updateUser(AdminRegisterForm adminRegisterForm) {

        adminService.updateUser(adminRegisterForm);

        return "redirect:/admin/accountList";
    }

}
