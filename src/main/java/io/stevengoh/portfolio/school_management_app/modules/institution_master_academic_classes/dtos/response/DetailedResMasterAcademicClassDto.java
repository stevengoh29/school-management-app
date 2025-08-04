package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResMasterAcademicClassDto extends WithAuditingBaseResponseMapperDto {
    private String name; // e.g., "1A", "2B", "TK B1"
    private String code; // e.g., "SD1A", "TKB1"
    private Integer orderIndex;
    private SimpleResInstitutionDto institution;
    private SimpleResGradeLevelDto gradeLevel;
}