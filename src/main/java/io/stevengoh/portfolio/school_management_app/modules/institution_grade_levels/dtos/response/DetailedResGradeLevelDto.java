package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response.SimpleResAcademicLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResGradeLevelDto extends WithAuditingBaseResponseMapperDto {
    private String code;
    private String name;
    private Integer orderIndex;
    private SimpleResInstitutionDto institution;
    private SimpleResAcademicLevelDto academicLevel;
}