package io.stevengoh.portfolio.school_management_app.modules.user_institution_roles;

import io.stevengoh.portfolio.school_management_app.modules.user_institution_roles.entities.UserInstitutionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserInstitutionRoleRepository extends JpaRepository<UserInstitutionRole, Long>, JpaSpecificationExecutor<UserInstitutionRole> {
    Optional<UserInstitutionRole> findByUuid(UUID uuid);
}
