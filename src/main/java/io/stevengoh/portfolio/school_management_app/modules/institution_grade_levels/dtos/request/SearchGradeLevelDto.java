package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchGradeLevelDto {
    private String code;
    private String name;
    private Integer orderIndex;
}