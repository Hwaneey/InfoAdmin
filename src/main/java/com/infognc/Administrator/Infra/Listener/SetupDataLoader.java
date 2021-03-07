package com.infognc.Administrator.Infra.Listener;

import com.infognc.Administrator.Infra.Config.PasswordConfig;

import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Role.Role;
import com.infognc.Administrator.Modules.Role.RoleRepository;
import com.infognc.Administrator.Modules.RoleHierarchy.RoleHierarchy;
import com.infognc.Administrator.Modules.RoleHierarchy.RoleHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Component @RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final RoleHierarchyRepository roleHierarchyRepository;
    private final PasswordConfig passwordConfig;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup){
            return;
        }
        setupSecurityResources();
        alreadySetup = true;
    }


    private void setupSecurityResources() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        Role middleAdminRole = createRoleIfNotFound("ROLE_MIDDLE_ADMIN", "중간관리자");
        Role qcRole = createRoleIfNotFound("ROLE_QC", "QC");
        Role consultantRole = createRoleIfNotFound("ROLE_CONSULTANT", "상담원");

        createRoleHierarchyIfNotFound(middleAdminRole, adminRole);
        createRoleHierarchyIfNotFound(qcRole, middleAdminRole);
        createRoleHierarchyIfNotFound(consultantRole, qcRole);

        roles.add(adminRole);

        createUserIfNotFound("admin", "123", roles);

    }


    @Transactional
    public Account createUserIfNotFound(final String userName, final String password, Set<Role> roleSet) {
        Account account = accountRepository.findByAgentId(userName);

        if (account == null) {
            account = Account.builder()
                    .agentNum("1")
                    .agentId(userName)
                    .agentName("관리자")
                    .password(passwordConfig.passwordEncoder().encode(password))
                    .part("관리자")
                    .level("1")
                    .agentCallNum("01034263345")
                    .status("1")
                    .regDate(LocalDateTime.now())
                    .delFlag("N")
                    .userRoles(roleSet)
                    .build();
        }
        return accountRepository.save(account);
    }


    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }


    @Transactional
    public void createRoleHierarchyIfNotFound(Role childRole, Role parentRole) {
        RoleHierarchy roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(parentRole.getRoleName())
                    .build();
        }
        RoleHierarchy parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        roleHierarchy = roleHierarchyRepository.findByChildName(childRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(childRole.getRoleName())
                    .build();
        }
        RoleHierarchy childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        childRoleHierarchy.setParentName(parentRoleHierarchy);
    }


}
