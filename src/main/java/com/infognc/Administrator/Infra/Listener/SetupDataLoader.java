package com.infognc.Administrator.Infra.Listener;

import com.infognc.Administrator.Infra.Config.PasswordConfig;
import com.infognc.Administrator.Modules.Account.AccountEntity;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.Role.RoleEntity;
import com.infognc.Administrator.Modules.Role.RoleRepository;
import com.infognc.Administrator.Modules.RoleHierachy.RoleHierarchyEnity;
import com.infognc.Administrator.Modules.RoleHierachy.RoleHierarchyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.lang.ref.PhantomReference;
import java.util.HashSet;
import java.util.Set;

@Component @RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
//    private final AccountEntity accountEntity;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordConfig;
    private final RoleHierarchyRepository roleHierarchyRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup){
            return;
        }
        setupSecurityResources();
        alreadySetup = true;
    }

    private void setupSecurityResources() {
        RoleEntity adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        RoleEntity middleAdminRole = createRoleIfNotFound("ROLE_MIDDLEMADMIN", "중간관리자");
        RoleEntity qcRole = createRoleIfNotFound("ROLE_QC", "QC");
        RoleEntity consultantRole = createRoleIfNotFound("ROLE_CONSULTANT", "상담원");

        createRoleHierarchyIfNotFound(middleAdminRole, adminRole);
        createRoleHierarchyIfNotFound(qcRole, middleAdminRole);
        createRoleHierarchyIfNotFound(consultantRole, qcRole);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(adminRole);
        createUserIfNotFound("admin", "admin@admin.com", "123123123", roles);
    }

    @Transactional
    public AccountEntity createUserIfNotFound(final String userName, final String email, final String password, Set<RoleEntity> roleSet) {
        AccountEntity account = accountRepository.findByAgentId(userName);
        if (account == null) {
            account = AccountEntity.builder()
                    .agentId(userName)
                    .password(passwordConfig.passwordEncoder().encode(password))
                    .agentName("관리자")
                    .userRoles(roleSet)
                    .agentNum("1")
                    .part("관리자")
                    .status("관리자")
                    .build();
        }
        return accountRepository.save(account);
    }


    @Transactional
    public RoleEntity createRoleIfNotFound(String roleName, String roleDesc) {
        RoleEntity roleEntity = roleRepository.findByRoleName(roleName);
        if (roleEntity == null) {
            roleEntity = RoleEntity.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(roleEntity);
    }


    @Transactional
    public void createRoleHierarchyIfNotFound(RoleEntity childRole, RoleEntity parentRole) {

        RoleHierarchyEnity roleHierarchyEnity = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
        if (roleHierarchyEnity == null) {
            roleHierarchyEnity = roleHierarchyEnity.builder()
                    .childName(parentRole.getRoleName())
                    .build();
        }

        RoleHierarchyEnity parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchyEnity);
        roleHierarchyEnity = roleHierarchyRepository.findByChildName(childRole.getRoleName());

        if (roleHierarchyEnity == null) {
            roleHierarchyEnity = RoleHierarchyEnity.builder()
                    .childName(childRole.getRoleName())
                    .build();
        }

        RoleHierarchyEnity childRoleHierarchy = roleHierarchyRepository.save(roleHierarchyEnity);

        childRoleHierarchy.setParentName(parentRoleHierarchy);
    }


}
