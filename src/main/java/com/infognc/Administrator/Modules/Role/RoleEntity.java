package com.infognc.Administrator.Modules.Role;

import com.infognc.Administrator.Modules.Account.AccountEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @EqualsAndHashCode(of = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RoleEntity {

    @Id  @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<AccountEntity> accountEntities = new HashSet<>();
}
