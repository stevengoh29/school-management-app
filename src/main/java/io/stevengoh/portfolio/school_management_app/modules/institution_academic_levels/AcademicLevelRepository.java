package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.entities.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcademicLevelRepository extends JpaRepository<AcademicLevel, Long>, JpaSpecificationExecutor<AcademicLevel> {
    Optional<AcademicLevel> findByUuid(UUID uuid);
}