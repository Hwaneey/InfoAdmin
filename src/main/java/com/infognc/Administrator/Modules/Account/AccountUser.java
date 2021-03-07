package com.infognc.Administrator.Modules.Account;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AccountUser extends User {

    private Account account;

    public AccountUser(Account account, List<GrantedAuthority> roles) {
        super(account.getAgentId(), account.getPassword(), roles);
        this.account = account;
    }
}
