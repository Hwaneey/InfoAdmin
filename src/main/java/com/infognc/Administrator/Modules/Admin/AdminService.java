package com.infognc.Administrator.Modules.Admin;


import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Role.Role;
import com.infognc.Administrator.Modules.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    @Transactional
    public AdminRegisterForm getUser(Long id) {

        Account account = accountRepository.findById(id).orElse(new Account());
        AdminRegisterForm adminRegisterForm = modelMapper.map(account, AdminRegisterForm.class);

        List<String> roles = account.getUserRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());

        adminRegisterForm.setRoles(roles);
        return adminRegisterForm;
    }


    @Transactional
    public void createUser(Account account, String level) {
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName(level);
        roles.add(role);
        account.setUserRoles(roles);
        account.setRegDate(LocalDateTime.now());
        accountRepository.save(account);
    }


    @Transactional
    public void updateUser(AdminRegisterForm adminRegisterForm){
        Account updateUser = accountRepository.findByAgentId(adminRegisterForm.getAgentId());
        Account accounts = modelMapper.map(adminRegisterForm,Account.class);

        if(adminRegisterForm.getRoles() != null){
            Set<Role> roles = new HashSet<>();
            adminRegisterForm.getRoles().forEach(role -> {
                Role r = roleRepository.findByRoleName(role);
                roles.add(r);
            });
            accounts.setUserRoles(roles);
        }

        if(!adminRegisterForm.getPassword().equals(updateUser.getPassword())){
            accounts.setPassword(passwordEncoder.encode(adminRegisterForm.getPassword()));
            accounts.setPwDate(LocalDateTime.now());
        }
        accountRepository.save(accounts);
    }


}
