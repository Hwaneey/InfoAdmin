package com.infognc.Administrator.Rest;

import com.infognc.Administrator.Modules.Account.Account;
import com.infognc.Administrator.Modules.Account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


//http://localhost:8080/api/userid/admin/password/123
@RestController @RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Login {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @GetMapping("/userid/{id}/password/{password}")
    public String kotlinLogin(@PathVariable("id") String agentId, @PathVariable("password") String password){
        Account account = accountRepository.findByAgentId(agentId);

        if(account == null){
            return "False";
        }

        if (!passwordEncoder.matches(password, account.getPassword())){

            return "False";
        }
        account.setLastDate(LocalDateTime.now());
        return "OK";
    }
}