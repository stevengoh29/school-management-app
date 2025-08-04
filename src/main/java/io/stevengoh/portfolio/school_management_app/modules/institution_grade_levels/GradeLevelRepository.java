package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels;

import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GradeLevelRepository extends JpaRepository<GradeLevel, Long>, JpaSpecificationExecutor<GradeLevel> {
    Optional<GradeLevel> findByUuid(UUID uuid);
}
