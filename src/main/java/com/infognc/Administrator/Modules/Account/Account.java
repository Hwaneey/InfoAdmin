package com.infognc.Administrator.Modules.Account;

import com.infognc.Administrator.Modules.Role.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    private String agentNum;

    @Column(unique = true)
    private String agentId;

    private String agentName;

    private String password;

    private String part;

    private String level ="3";

    private String agentCallNum;

    private String status = "1";

    private LocalDateTime regDate;

    private LocalDateTime lastDate;

    private LocalDateTime pwDate;

    private String delFlag = "N";

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> userRoles = new HashSet<>();

}