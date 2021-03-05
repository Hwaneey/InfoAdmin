package com.infognc.Administrator.Modules.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    RoleEntity findByRoleName(String name);

    @Override
    void deleteById(Long aLong);
}
