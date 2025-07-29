package io.stevengoh.portfolio.school_management_app.modules.user_roles;

import io.stevengoh.portfolio.school_management_app.modules.user_roles.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {
    Optional<UserRole> findByUuid(UUID uuid);
}
