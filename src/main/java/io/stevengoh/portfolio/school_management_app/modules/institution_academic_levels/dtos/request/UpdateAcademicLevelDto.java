package io.stevengoh.portfolio.school_management_app.modules.institution_academic_levels.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAcademicLevelDto {
    private String name;
    private Integer orderIndex;
}