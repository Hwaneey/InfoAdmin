package com.infognc.administrator.Account;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    boolean existsByAgentId(String AgentId);

    AccountEntity findByAgentId(String AgentId);

}
