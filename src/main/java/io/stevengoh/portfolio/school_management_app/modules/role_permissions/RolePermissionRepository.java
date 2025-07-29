package io.stevengoh.portfolio.school_management_app.modules.role_permissions;

import io.stevengoh.portfolio.school_management_app.modules.role_permissions.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>, JpaSpecificationExecutor<RolePermission> {
    Optional<RolePermission> findByUuid(UUID uuid);

    List<RolePermission> findAllByUuidIn(Iterable<UUID> uuids);
}