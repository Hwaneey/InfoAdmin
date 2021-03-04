package com.infognc.Administrator.Infra.Listener;

import com.infognc.Administrator.Infra.Config.PasswordConfig;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import com.infognc.Administrator.Modules.RoleHierachy.RoleHierarchyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener {

    private boolean alreadySetup = false;
    private final AccountRepository accountRepository;
//    private final RoleHierarchyEntity
    private final PasswordConfig passwordConfig;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    if (alreadySetup){
        return;
    }
    alreadySetup = true;
    }

}
