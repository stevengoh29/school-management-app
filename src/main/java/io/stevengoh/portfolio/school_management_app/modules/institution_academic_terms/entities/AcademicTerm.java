package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities;

import io.stevengoh.portfolio.school_management_app.common.entities.BaseEntity;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.enums.TermStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "academic_terms")
@Getter
@Setter
public class AcademicTerm extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private TermStatus status = TermStatus.ACTIVE; // ACTIVE, INACTIVE, ARCHIVED

    private Boolean isCurrent = Boolean.FALSE;
}