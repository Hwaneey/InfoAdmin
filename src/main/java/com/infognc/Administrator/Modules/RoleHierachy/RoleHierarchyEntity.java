package com.infognc.Administrator.Modules.RoleHierachy;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class RoleHierarchyEntity implements Serializable {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "child_name")
    private RoleHierarchyEntity parentName;

    @OneToMany(mappedBy = "parentName", cascade={CascadeType.ALL})
    private Set<RoleHierarchyEntity> roleHierarchyEntities = new HashSet<RoleHierarchyEntity>();
}
