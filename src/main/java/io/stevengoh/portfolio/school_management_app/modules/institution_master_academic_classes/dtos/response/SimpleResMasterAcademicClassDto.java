package io.stevengoh.portfolio.school_management_app.modules.institution_master_academic_classes.dtos.response;

import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponseMapperDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResMasterAcademicClassDto extends BaseResponseMapperDto {
    private String name; // e.g., "1A", "2B", "TK B1"
    private String code; // e.g., "SD1A", "TKB1"
    private Integer orderIndex;
}