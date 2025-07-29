package io.stevengoh.portfolio.school_management_app.modules.roles;

import io.stevengoh.portfolio.school_management_app.modules.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findByUuid(UUID uuid);
}
