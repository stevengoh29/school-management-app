package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.dtos.response.SimpleResAcademicTermDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_academic_terms.entities.AcademicTerm;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response.SimpleResGradeLevelDto;
import io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.entities.GradeLevel;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.SimpleResInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.entities.Institution;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedResAcademicClassDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private String code;
    private Integer orderIndex;
    private SimpleResAcademicTermDto academicTerm;
    private SimpleResGradeLevelDto gradeLevel;
    private SimpleResInstitutionDto institution;
    private Boolean isActive;
}