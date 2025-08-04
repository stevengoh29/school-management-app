package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.entities.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcademicClassRepository extends JpaRepository<AcademicClass, Long>, JpaSpecificationExecutor<AcademicClass> {
    Optional<AcademicClass> findByUuid(UUID uuid);
}
