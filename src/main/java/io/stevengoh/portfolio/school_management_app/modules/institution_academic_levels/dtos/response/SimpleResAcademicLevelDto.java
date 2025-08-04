package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.WithAuditingBaseResponseMapperDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResAcademicLevelDto extends WithAuditingBaseResponseMapperDto {
    private String name;
    private Integer orderIndex;
}
