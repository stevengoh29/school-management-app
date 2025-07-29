package io.stevengoh.portfolio.school_management_app.modules.institutions;

import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import io.stevengoh.portfolio.school_management_app.modules.institutions.enums.InstitutionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>, JpaSpecificationExecutor<Institution> {
    boolean existsByCode(String code);
    Optional<Institution> findByUuid(UUID uuid);
    Optional<Institution> findByUuidAndStatus(UUID uuid, InstitutionStatus status);
}
