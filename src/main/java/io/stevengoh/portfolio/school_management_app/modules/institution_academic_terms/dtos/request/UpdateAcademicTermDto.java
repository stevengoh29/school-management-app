package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request;

import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.enums.TermStatus;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateAcademicTermDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private TermStatus status;
    private Boolean isCurrent;
}