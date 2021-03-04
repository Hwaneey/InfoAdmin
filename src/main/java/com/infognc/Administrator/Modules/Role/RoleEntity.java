package com.infognc.Administrator.Modules.Role;

import com.infognc.Administrator.Modules.Account.AccountEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @EqualsAndHashCode(of = "id")
@Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id  @GeneratedValue @Column
    private Long id;

    @Column
    private String roleName;

    @Column
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<AccountEntity> accounts = new HashSet<>();
}
