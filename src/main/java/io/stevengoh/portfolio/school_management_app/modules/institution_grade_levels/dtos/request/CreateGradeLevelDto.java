package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateGradeLevelDto implements InstitutionAware {
    private Institution institution;
    private String code;
    private String name;
    private Integer orderIndex;
    private UUID academicLevelUuid;
}
