package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long>, JpaSpecificationExecutor<AcademicTerm> {
    Optional<AcademicTerm> findByUuid(UUID uuid);
    List<AcademicTerm> findAllByInstitution_Uuid(UUID institutionUuid);
}