package com.infognc.Administrator.Modules.Admin;

import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Account.AuthenticationPrincipalAccount;
import com.infognc.Administrator.Modules.Admin.Validator.AdminRegisterFormValidator;
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
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRegisterFormValidator adminRegisterFormValidator;

    @InitBinder("adminRegisterForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(adminRegisterFormValidator);
    }

    @GetMapping("/admin/accountList")
    public String getUsers(Model model,
                           @PageableDefault(size = 30, direction = Sort.Direction.DESC) Pageable pageable) {

        List<Account> accounts = accountRepository.findAll();
        Page<Account> accountPage = accountRepository.findAll(pageable);

        model.addAttribute("accountPage", accountPage);
        model.addAttribute("accounts", accounts);

        return "admin/accountList";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam(value = "status", required = false, defaultValue = "") String status,
                         @RequestParam(value = "agentId", required = false, defaultValue = "") String agentId,
                         @RequestParam(value = "agentCallNum", required = false, defaultValue = "") String agentCallNum,
                         @RequestParam(value = "agentNum", required = false, defaultValue = "") String agentNum,
                         @RequestParam(value = "part", required = false, defaultValue = "") String part,
                         @RequestParam(value = "level", required = false, defaultValue = "") String level,
                         @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        List<Account> accountPage =
                accountRepository.findByStatusContainingAndAgentIdContainingAndAgentCallNumContainingAndAgentNumContainingAndPartContainingAndLevelContaining
                (status, agentId, agentCallNum, agentNum, part, level);

        model.addAttribute("accountPage", accountPage);
        return "admin/accountSearch";
    }


    @GetMapping("/admin/accountRegister")
    public String getRegisterUser(Model model) {

        List<Role> roleList = adminService.getRoles();

        model.addAttribute("roleList", roleList);

        model.addAttribute(new AdminRegisterForm());

        return "admin/accountRegister";
    }


    @PostMapping("/accountRegister")
    public String registerUser(@Valid AdminRegisterForm adminRegisterForm, Errors errors, Model model) {

        if (errors.hasErrors()){
            List<Role> roleList = adminService.getRoles();
            model.addAttribute("roleList", roleList);
            return "admin/accountRegister";
        }

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
    public String updateUser(Account account,AdminRegisterForm adminRegisterForm, Model model, Errors errors) {

        adminService.updateUser(adminRegisterForm);
        return "redirect:/admin/accountList";
    }

}
