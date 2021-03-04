package com.infognc.Administrator.Modules.Account;

import com.infognc.Administrator.Modules.Role.RoleEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = "idxAgent")
@Entity @Getter @Setter @NoArgsConstructor
@AllArgsConstructor @Builder
public class AccountEntity {

    @Id
    @GeneratedValue @Column
    private int idxAgent;

    @NotNull
    private String agentId;

    @NotNull
    private String agentNum;

    @NotNull
    private String agentName;

    @NotNull
    private String part;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "agentId") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<RoleEntity> userRoles = new HashSet<>();

    @NotNull
    private String level;

    @NotNull
    private String status;

    @NotNull
    private String password;

    @NotNull
    private String regDate;

    private LocalDateTime lastDate;

    private LocalDateTime pwDate;

    @NotNull
    private String delFlag;

}
