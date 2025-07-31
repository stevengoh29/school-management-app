package io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateAcademicTermDto implements InstitutionAware {
    private Institution institution;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}