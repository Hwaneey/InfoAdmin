package com.infognc.Administrator.Modules.Account;

import com.infognc.Administrator.Modules.Role.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id")
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(length = 8)
    private String agentNum;

    @Column(length = 10, unique = true)
    private String agentId;

    @Column(length = 10)
    private String agentName;

    private String password;

    private String part;

    private String level;

    private String agentCallNum;

    private String status;

    private LocalDateTime regDate;

    private LocalDateTime lastDate;

    private LocalDateTime pwDate;

    private String delFlag;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> userRoles = new HashSet<>();

}