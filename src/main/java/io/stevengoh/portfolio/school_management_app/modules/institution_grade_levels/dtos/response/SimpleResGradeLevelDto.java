package io.stevengoh.portfolio.school_management_app.modules.institution_grade_levels.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResGradeLevelDto extends BaseResponseMapperDto {
    private String code;
    private String name;
    private Integer orderIndex;
}
