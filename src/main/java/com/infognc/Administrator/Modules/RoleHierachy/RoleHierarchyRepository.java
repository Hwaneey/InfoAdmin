package com.infognc.Administrator.Modules.RoleHierachy;


import com.infognc.Administrator.Modules.Role.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchyEnity, Long> {

    RoleHierarchyEnity findByChildName(String roleName);
}
