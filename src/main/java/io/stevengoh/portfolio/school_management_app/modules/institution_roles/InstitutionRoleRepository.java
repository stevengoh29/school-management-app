package io.stevengoh.portfolio.school_management_app.modules.institution_roles;

import io.stevengoh.portfolio.school_management_app.modules.institution_roles.entities.InstitutionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRoleRepository extends JpaRepository<InstitutionRole, Long>, JpaSpecificationExecutor<InstitutionRole> {
    Optional<InstitutionRole> findByUuid(UUID uuid);
}
