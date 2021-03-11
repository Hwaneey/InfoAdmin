package com.infognc.Administrator.Modules.Account;

import com.infognc.Administrator.Modules.Role.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@ToString(exclude = {"userRoles"})
@EqualsAndHashCode(of = "id") @Table(name = "account_list")
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "agentNum")
    private String agentNum;

    @Column(unique = true, name = "agentId")
    private String agentId;

    @Column(name = "agentName")
    private String agentName;

    @Column(name = "password")
    private String password;

    @Column(name = "part")
    private String part;

    @Column(name = "level")
    private String level ="3";

    @Column(name = "agentCallnum")
    private String agentCallNum;

    @Column(name = "status")
    private String status = "1";

    @Column(name = "regDate")
    private LocalDateTime regDate;

    @Column(name = "lastDate")
    private LocalDateTime lastDate;

    @Column(name = "pwDate")
    private LocalDateTime pwDate;

    @Column(name = "delFlag")
    private String delFlag = "N";

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(
            name = "account_roles",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> userRoles = new HashSet<>();

}