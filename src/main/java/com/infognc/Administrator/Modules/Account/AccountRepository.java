package com.infognc.Administrator.Modules.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByAgentId(String username);

    Page<Account> findAll(Pageable pageable);

    List<Account> findByAgentIdContaining(String keyword);
}
