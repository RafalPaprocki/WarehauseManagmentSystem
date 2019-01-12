package com.warehausesystem.app.warehauseSystemWeb.repository;

import java.util.Optional;


import com.warehausesystem.app.warehauseSystemWeb.model.Role;
import com.warehausesystem.app.warehauseSystemWeb.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
