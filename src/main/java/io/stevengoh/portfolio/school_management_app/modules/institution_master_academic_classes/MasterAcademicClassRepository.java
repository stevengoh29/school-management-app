package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes;

import io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.entities.MasterAcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MasterAcademicClassRepository extends JpaRepository<MasterAcademicClass, Long>, JpaSpecificationExecutor<MasterAcademicClass> {
    Optional<MasterAcademicClass> findByUuid(UUID uuid);
}