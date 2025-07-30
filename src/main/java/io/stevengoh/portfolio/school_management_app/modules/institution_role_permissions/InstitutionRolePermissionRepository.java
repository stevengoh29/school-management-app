package io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions;

import io.stevengoh.portfolio.school_management_app.modules.institution_role_permissions.entities.InstitutionRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRolePermissionRepository extends JpaRepository<InstitutionRolePermission, Long>,
        JpaSpecificationExecutor<InstitutionRolePermission> {
    Optional<InstitutionRolePermission> findByUuid(UUID uuid);
    List<InstitutionRolePermission> findAllByUuidIn(List<UUID> uuids);
}