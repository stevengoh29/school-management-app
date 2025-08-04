package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAcademicLevelDto implements InstitutionAware {
    private String name;
    private Integer orderIndex;
    private Institution institution;
}