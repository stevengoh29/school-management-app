package io.stevengoh.portfolio.school_management_app.modules.permissions;

import io.stevengoh.portfolio.school_management_app.modules.permissions.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>,
        JpaSpecificationExecutor<Permission> {
    Optional<Permission> findByUuid(UUID uuid);
}