package com.infognc.Administrator.Modules.RoleHierachy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchyEntity, Long> {

    RoleHierarchyEntity findByChildName(String roleName);
}
