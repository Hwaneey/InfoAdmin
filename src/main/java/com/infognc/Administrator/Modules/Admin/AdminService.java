package com.infognc.Administrator.Modules.Admin;


import com.infognc.Administrator.Infra.Config.PasswordConfig;
import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Role.Role;
import com.infognc.Administrator.Modules.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AccountRepository accountRepository;
    private final PasswordConfig passwordConfig;
    private final RoleRepository roleRepository;
    private final Set<Role> roles = new HashSet<>();

    @Transactional
    public void createUser(Account account) {
        Role role = roleRepository.findByRoleName("ROLE_QC");
        roles.add(role);
        account.setRegDate(LocalDateTime.now());
        account.setUserRoles(roles);
        accountRepository.save(account);
    }

    @Transactional
    public List<Account> getUsers() {
        return accountRepository.findAll();
    }


}
