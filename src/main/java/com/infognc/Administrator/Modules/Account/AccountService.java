package com.infognc.Administrator.Modules.Account;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AccountService extends User  {

    private final AccountEntity accountEntity;

    public AccountService(AccountEntity accountEntity) {
        super(accountEntity.getAgentId(), accountEntity.getPassword(),List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.accountEntity = accountEntity;
    }
}
