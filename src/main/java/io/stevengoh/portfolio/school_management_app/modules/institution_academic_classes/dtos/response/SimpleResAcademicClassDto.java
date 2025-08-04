package io.stevengoh.portfolio.school_management_app.modules.institution_academic_classes.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResAcademicClassDto extends BaseResponseMapperDto {
    private String name;
    private String code;
    private Integer orderIndex;
    private Boolean isActive;
}
