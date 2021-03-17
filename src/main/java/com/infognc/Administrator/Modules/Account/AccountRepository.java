package com.infognc.Administrator.Modules.Account;

import com.sun.istack.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {

    Account findByAgentId(String username);

    Page<Account> findAll(Pageable pageable);

    List<Account> findByStatusContainingAndAgentIdContainingAndAgentCallNumContainingAndAgentNumContainingAndPartContainingAndLevelContaining(String Status,String AgentId, String AgentCallNum, String AgentNum,String Part, String Level);

    Page<Account> findAll(@Nullable Specification<Account> spec, Pageable pageable);

    boolean existsByAgentId(String agentId);

    boolean existsByAgentNum(String agentNum);
}
