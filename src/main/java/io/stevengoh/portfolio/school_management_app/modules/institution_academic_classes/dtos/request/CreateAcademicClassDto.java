package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.request;

import io.stevengoh.portfolio.school_management_app.common.interfaces.InstitutionAware;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateAcademicClassDto implements InstitutionAware {
    private Institution institution;
    private String name;
    private String code;
    private Integer orderIndex;
    private UUID gradeLevelUuid;
}